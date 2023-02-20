package com.mgo.resource;

import com.mgo.entity.City;
import com.mgo.model.city.CityModel;
import com.mgo.model.city.CitySearchResultModel;
import com.mgo.tracer.TraceLog;

import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Metered;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.opentracing.Traced;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import java.util.*;
import java.util.stream.Collectors;

@Path("/cities")
@Tag(name = "City API")
public class CityApi {
    @GET
    @Path("/")
    @TraceLog
    @Traced(operationName = "/cities")
    @Counted(name = "citiesReadCount")
    @Metered(name = "citiesReadMeter")
    @Timed(name = "citiesReadTime")
   // @RolesAllowed({ "city", "city.readonly" })
    @Operation(operationId = "cities", summary = "Get cities")
    @APIResponse(responseCode = "200", content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(ref = "CitySearchResult")))
    @APIResponse(responseCode = "401", ref = "Unauthorized")
    @APIResponse(responseCode = "403", ref = "Forbidden")
    @APIResponse(responseCode = "404", ref = "NotFound")
    @APIResponse(responseCode = "500", ref = "InternalError")
    @Produces(MediaType.APPLICATION_JSON)
    public CitySearchResultModel cities(
            @Context
            SecurityContext securityContext
    ) {


       List<CityModel> items =  City.listAll().stream().map(c -> new CityModel((City) c)).collect(Collectors.toList());
       CitySearchResultModel resultModel = new CitySearchResultModel();
       resultModel.setItems(items);
       resultModel.setTotalCount(items.size());

       return  resultModel;
    }

}