package com.mgo.resource;

import com.mgo.auth.AuthService;
import com.mgo.auth.AuthenticatedUser;
import com.mgo.entity.Person;
import com.mgo.model.common.OffsetPaging;
import com.mgo.model.person.PersonSearchFilterModel;
import com.mgo.model.person.PersonSearchResultModel;
import com.mgo.model.person.PersonModel;
import com.mgo.model.person.PersonSearchSortModel;
import com.mgo.qs.QS;
import com.mgo.qs.model.ParseOptions;
import com.mgo.qs.parser.ParseException;
import com.mgo.service.person.PersonService;
import com.mgo.tracer.TraceLog;
import io.quarkus.security.Authenticated;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Metered;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.Explode;
import org.eclipse.microprofile.openapi.annotations.enums.ParameterIn;
import org.eclipse.microprofile.openapi.annotations.enums.ParameterStyle;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.opentracing.Traced;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/people")
@Tag(name = "People API")
public class PeopleApi {
    @GET
    @Path("/")
    @TraceLog
    @Traced(operationName = "/people")
    @Counted(name = "peopleReadCount")
    @Metered(name = "peopleReadMeter")
    @Timed(name = "peopleReadTime")
   // @RolesAllowed({ "city", "city.readonly" })
    @Operation(operationId = "people", summary = "Get people")
    @APIResponse(responseCode = "200", content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(ref = "PersonSearchResult")))
    @APIResponse(responseCode = "401", ref = "Unauthorized")
    @APIResponse(responseCode = "403", ref = "Forbidden")
    @APIResponse(responseCode = "404", ref = "NotFound")
    @APIResponse(responseCode = "500", ref = "InternalError")
    @Produces(MediaType.APPLICATION_JSON)
    @Authenticated
    public PersonSearchResultModel searchPeople(
            @Parameter(name = "q", description = "The search text", in = ParameterIn.QUERY, schema = @Schema(type = SchemaType.STRING))
            @QueryParam("q") String q,

            @Parameter(name = "paging", description = "The paging", in = ParameterIn.QUERY, schema = @Schema(ref = "OffsetPaging"), explode = Explode.TRUE, style = ParameterStyle.DEEPOBJECT)
            @QueryParam("paging")
            OffsetPaging paging,

            @Parameter(name = "filter", description = "The filter", in = ParameterIn.QUERY, schema = @Schema(ref = "PersonSearchFilter"), explode = Explode.TRUE, style = ParameterStyle.DEEPOBJECT)
            @QueryParam("filter")
            PersonSearchFilterModel filter,

            @Parameter(name = "sort", description = "The sorting", in = ParameterIn.QUERY, schema = @Schema(ref = "PersonSearchSort"),explode = Explode.TRUE, style = ParameterStyle.DEEPOBJECT)
            @QueryParam("sort")
            PersonSearchSortModel sort,

            @Context UriInfo uriInfo,

            @Context SecurityContext securityContext
    ) throws ParseException {

        AuthenticatedUser user = AuthService.getAuthenticatedUser(securityContext);

       return PersonService.searchPeople(user,uriInfo);
    }

}