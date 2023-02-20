package com.mgo.resource;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import com.mgo.auth.AuthService;
import com.mgo.auth.AuthenticatedUser;
import com.mgo.entity.Departure;
import com.mgo.model.common.OffsetPaging;
import com.mgo.model.departure.*;
import com.mgo.service.departure.DepartureService;
import com.mgo.tracer.TraceLog;

import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Metered;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.Explode;
import org.eclipse.microprofile.openapi.annotations.enums.ParameterIn;
import org.eclipse.microprofile.openapi.annotations.enums.ParameterStyle;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.opentracing.Traced;

import io.quarkus.security.Authenticated;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static org.eclipse.microprofile.openapi.annotations.enums.SchemaType.ARRAY;

import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

@Path("departures")
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
@Tag(name = "Departure API")
@ApplicationScoped
public class DepartureApi {

    @DELETE
    @Path("/{departureId}")
    @TraceLog
    @Traced(operationName = "/{departureId}")
    @Counted(name = "deleteDepartureCount")
    @Metered(name = "deleteDepartureMeter")
    @Timed(name = "deleteDepartureTime")
    @Operation(operationId = "deleteDeparture", summary = "Delete departure")
    @APIResponse(responseCode = "200")
    @APIResponse(responseCode = "401", ref = "Unauthorized")
    @APIResponse(responseCode = "403", ref = "Forbidden")
    @APIResponse(responseCode = "404", ref = "NotFound")
    @APIResponse(responseCode = "500", ref = "InternalError")
    @Authenticated
    public Object deleteDeparture(
            @Parameter(name = "departureId", description = "The id of departure", required = true, in = ParameterIn.PATH, schema = @Schema(type = SchemaType.STRING))
            @PathParam("departureId") String departureId,
            @Context SecurityContext securityContext

    ){
        Departure departure = Departure.findById( departureId);
        if(departure==null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        AuthenticatedUser user= AuthService.getAuthenticatedUser(securityContext);

        DepartureService.deleteDeparture(user,departure);
        return Response.status(Response.Status.OK).build();
    }


    @GET
    @Path("/{departureId}")
    @TraceLog
    @Traced(operationName = "/{departureId}")
    @Counted(name = "departureCount")
    @Metered(name = "departureMeter")
    @Timed(name = "departureTime")
    @Operation(operationId = "departure", summary = "Get departure")
    @APIResponse(responseCode = "200", content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(ref = "Departure" )))
    @APIResponse(responseCode = "401", ref = "Unauthorized")
    @APIResponse(responseCode = "403", ref = "Forbidden")
    @APIResponse(responseCode = "404", ref = "NotFound")
    @APIResponse(responseCode = "500", ref = "InternalError")
    @Produces(MediaType.APPLICATION_JSON)
    @Authenticated
    public Object departure(
            @Parameter(name = "departureId", description = "The id of departure", required = true, in = ParameterIn.PATH, schema = @Schema(type = SchemaType.STRING))
            @PathParam("departureId") String departureId
    ){
        Departure departure = Departure.findById(departureId);
        if(departure==null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return new DepartureModel(departure, DepartureService.findDepartureOrganizationConfig(departure) , new Date());
    }


    @POST
    @Path("/")
    @TraceLog
    @Traced(operationName = "/departures")
    @Counted(name = "departuresCreationCount")
    @Metered(name = "departuresCreationMeter")
    @Timed(name = "departuresCreationTime")
    @RequestBody(ref = "DepartureCreate")
    @Operation(operationId = "createDeparture", summary = "Create Departure")
    @APIResponse(responseCode = "200", content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(ref = "Departure")))
    @APIResponse(responseCode = "401", ref = "Unauthorized")
    @APIResponse(responseCode = "403", ref = "Forbidden")
    @APIResponse(responseCode = "500", ref = "InternalError")
    @Produces(MediaType.APPLICATION_JSON)
    @Authenticated
    @Transactional
    public Object createDeparture(
            DepartureCreateModel departureCreate,
            @Context SecurityContext securityContext
    ) throws ParseException {
        AuthenticatedUser user = AuthService.getAuthenticatedUser(securityContext);
        Departure departure = DepartureService.createDeparture(user,departureCreate);
        return new DepartureModel(departure,DepartureService.findDepartureOrganizationConfig(departure),new Date());
    }


    @POST
    @Path("/bulk-create")
    @TraceLog
    @Traced(operationName = "/departures/bulk-create")
    @Counted(name = "departuresBulkCreationCount")
    @Metered(name = "departuresBulkCreationMeter")
    @Timed(name = "departuresBulkCreationTime")
    @RequestBody(ref = "DepartureBulkCreate")
    @Operation(operationId = "createBulkDepartures", summary = "Bulk Create Departure")
    @APIResponse(responseCode = "200", content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(ref = "Departure", type = ARRAY)))
    @APIResponse(responseCode = "401", ref = "Unauthorized")
    @APIResponse(responseCode = "403", ref = "Forbidden")
    @APIResponse(responseCode = "500", ref = "InternalError")
    @Produces(MediaType.APPLICATION_JSON)
    @Authenticated
    @Transactional
    public Object bulkCreateDeparture(
            DepartureBulkCreateModel departureCreate,
            @Context SecurityContext securityContext
    ) throws ParseException {
        AuthenticatedUser user = AuthService.getAuthenticatedUser(securityContext);
        Date now = new Date();
        return DepartureService.bulkCreateDepartures(user,departureCreate).stream().map(departure -> new DepartureModel(departure,DepartureService.findDepartureOrganizationConfig(departure),now)).collect(Collectors.toList());
    }


        @GET
        @Path("/")
        @TraceLog
        @Traced(operationName = "/departures")
        @Counted(name = "departuresReadCount")
        @Metered(name = "departuresReadMeter")
        @Timed(name = "departuresReadTime")
       // @RolesAllowed({ "departure", "departure.readonly" })
        @Operation(operationId = "searchDepartures", summary = "Search departures")
        @APIResponse(responseCode = "200", content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(ref = "DepartureSearchResult")))
        @APIResponse(responseCode = "401", ref = "Unauthorized")
        @APIResponse(responseCode = "403", ref = "Forbidden")
        @APIResponse(responseCode = "404", ref = "NotFound")
        @APIResponse(responseCode = "500", ref = "InternalError")
        public DepartureSearchResultModel searchDepartures(
                        @Parameter(name = "q", description = "The search text", in = ParameterIn.QUERY, schema = @Schema(type = SchemaType.STRING))
                        @QueryParam("q") String q,

                        @Parameter(name = "paging", description = "The paging", in = ParameterIn.QUERY, schema = @Schema(ref = "OffsetPaging"), explode = Explode.TRUE, style = ParameterStyle.DEEPOBJECT)
                        @QueryParam("paging")
                        OffsetPaging paging,

                        @Parameter(name = "filter", description = "The filter", in = ParameterIn.QUERY, schema = @Schema(ref = "DepartureSearchFilter"), explode = Explode.TRUE, style = ParameterStyle.DEEPOBJECT)
                        @QueryParam("filter")
                        DepartureSearchFilterModel filter,

                        @Parameter(name = "sort", description = "The sorting", in = ParameterIn.QUERY, schema = @Schema(ref = "DepartureSearchSort"),explode = Explode.TRUE, style = ParameterStyle.DEEPOBJECT)
                        @QueryParam("sort")
                        DepartureSearchSortModel sort,


                        @Parameter(name = "options", description = "The options", in = ParameterIn.QUERY, schema = @Schema(ref = "DepartureSearchOptions"), explode = Explode.TRUE, style = ParameterStyle.DEEPOBJECT)
                        @QueryParam("options")
                        DepartureSearchOptionsModel options,

                        @Context UriInfo uriInfo,

                        @Context SecurityContext securityContext



        ) throws ParseException, com.mgo.qs.parser.ParseException {

            AuthenticatedUser user = AuthService.getAuthenticatedUser(securityContext);
            return DepartureService.searchDepartures(user,uriInfo);
        }


        @GET
        @Path("/{departureId}/manifold.pdf")
        @TraceLog
        @Traced(operationName = "/{departureId}/manifold")
        @Counted(name = "departureManifoldCount")
        @Metered(name = "departureManifoldMeter")
        @Timed(name = "departureManifoldTime")
        @Operation(operationId = "departureManifold", summary = "Get departure manifold")
        @APIResponse(responseCode = "200", content = @Content(mediaType = "application/pdf", schema = @Schema(format = "binary" )))
        @APIResponse(responseCode = "401", ref = "Unauthorized")
        @APIResponse(responseCode = "403", ref = "Forbidden")
        @APIResponse(responseCode = "404", ref = "NotFound")
        @APIResponse(responseCode = "500", ref = "InternalError")
        @Produces("application/pdf")
        @Authenticated
        public Response departureManifold(
            @Parameter(name = "departureId", description = "The id of departure", required = true, in = ParameterIn.PATH, schema = @Schema(type = SchemaType.STRING))
            @PathParam("departureId") String departureId
        ){
                /*  BufferedImage image;
                 try {

                        Departure departure = Departure.find("departure_id = " + departureId).firstResult();
                        if(departure==null){
                                return Response.status(Response.Status.NOT_FOUND).build();
                        }        


                        List<DeparturemanifoldPlaceInfo> manifoldPlaceInfos = this.getManifoldPlaceInfos(departure);
                        Organization company = departure.getOrganization();

                        String html = export
                                        .data("company", company)
                                        .data("departure", departure)
                                        .data("departureDatetime", DateTimeFormat.formatDateHour(departure.getDateTime()))
                                        .data("manifoldPlaceInfos", manifoldPlaceInfos)
                                        .data("columns", departure.getColumns())
                                        .data("lines", departure.getLines())
                                        .data("columnWidth",
                                                        100 / departure.getColumns())
                                        .render();

                        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

                        ITextRenderer renderer = new ITextRenderer();
                        renderer.setDocumentFromString(html);
                        renderer.layout();
                        renderer.createPDF(outputStream);

                        outputStream.close();

                        return Response.ok(outputStream.toByteArray()).build();
                } catch (Exception e) {
                        e.printStackTrace();
                }*/

                return Response.serverError().build();
                
        }


}
