package com.mgo.resource;

import com.mgo.auth.AuthService;
import com.mgo.auth.AuthenticatedUser;
import com.mgo.entity.Organization;
import com.mgo.model.organization.OrganizationModel;
import com.mgo.model.organization.OrganizationSearchResultModel;
import com.mgo.service.organization.OrganizationService;
import com.mgo.tracer.TraceLog;

import io.quarkus.security.Authenticated;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Metered;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.ParameterIn;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.opentracing.Traced;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.List;
import java.util.stream.Collectors;

@Path("/organizations")
@Tag(name = "Organization API")
public class OrganizationApi {

    @DELETE
    @Path("/{organizationId}")
    @TraceLog
    @Traced(operationName = "/{organizationId}")
    @Counted(name = "deleteOrganizationCount")
    @Metered(name = "deleteOrganizationMeter")
    @Timed(name = "deleteOrganizationTime")
    @Operation(operationId = "deleteOrganization", summary = "Delete organization")
    @APIResponse(responseCode = "200")
    @APIResponse(responseCode = "401", ref = "Unauthorized")
    @APIResponse(responseCode = "403", ref = "Forbidden")
    @APIResponse(responseCode = "404", ref = "NotFound")
    @APIResponse(responseCode = "500", ref = "InternalError")
    @Authenticated
    public Object deleteOrganization(
            @Parameter(name = "organizationId", description = "The id of organization", required = true, in = ParameterIn.PATH, schema = @Schema(type = SchemaType.STRING))
            @PathParam("organizationId") String organizationId,
            @Context SecurityContext securityContext

    ){
        Organization organization = Organization.findById(organizationId);
        if(organization==null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        AuthenticatedUser user= AuthService.getAuthenticatedUser(securityContext);

        OrganizationService.deleteOrganization(user, organization);
        return Response.status(Response.Status.OK).build();
    }


    @GET
    @Path("/{organizationId}")
    @TraceLog
    @Traced(operationName = "/{organizationId}")
    @Counted(name = "organizationCount")
    @Metered(name = "organizationMeter")
    @Timed(name = "organizationTime")
    @Operation(operationId = "organization", summary = "Get Organization")
    @APIResponse(responseCode = "200", content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(ref = "Organization" )))
    @APIResponse(responseCode = "401", ref = "Unauthorized")
    @APIResponse(responseCode = "403", ref = "Forbidden")
    @APIResponse(responseCode = "404", ref = "NotFound")
    @APIResponse(responseCode = "500", ref = "InternalError")
    @Produces(MediaType.APPLICATION_JSON)
    @Authenticated
    public Object organization(
            @Parameter(name = "organizationId", description = "The id of organization", required = true, in = ParameterIn.PATH, schema = @Schema(type = SchemaType.STRING))
            @PathParam("organizationId") String bookingId
    ){
        Organization organization = Organization.findById(bookingId);
        if(organization==null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return new OrganizationModel(organization);
    }



    @GET
    @Path("/")
    @TraceLog
    @Traced(operationName = "/organizations")
    @Counted(name = "organizationsReadCount")
    @Metered(name = "organizationsReadMeter")
    @Timed(name = "organizationsReadTime")
   // @RolesAllowed({ "organization", "organization.readonly" })
    @Operation(operationId = "organizations", summary = "Get organizations")
    @APIResponse(responseCode = "200", content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(ref = "OrganizationSearchResult")))
    @APIResponse(responseCode = "401", ref = "Unauthorized")
    @APIResponse(responseCode = "403", ref = "Forbidden")
    @APIResponse(responseCode = "404", ref = "NotFound")
    @APIResponse(responseCode = "500", ref = "InternalError")
    
    @Produces(MediaType.APPLICATION_JSON)
    public OrganizationSearchResultModel organizations() {
        List<OrganizationModel> items =   Organization.listAll().stream().map(c-> new OrganizationModel((Organization) c)).collect(Collectors.toList());

        OrganizationSearchResultModel searchResultModel = new OrganizationSearchResultModel();
        searchResultModel.setItems(items);
        searchResultModel.setTotalCount(items.size());

        return searchResultModel;
    }

}