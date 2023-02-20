package com.mgo.auth;

import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Metered;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.opentracing.Traced;
import org.eclipse.microprofile.rest.client.inject.RestClient;


import com.mgo.tracer.TraceLog;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/oauth/token")
public class TokenEndpoint {
    @Inject
    @RestClient
    KeyCloakService service;


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Operation(hidden = true)
    @TraceLog
    @Traced(operationName = "/oauth/token")
    @Counted(name = "oauthTokenCount")
    @Metered(name = "oauthTokenMeter")
    @Timed(name = "oauthTokenTime")

    public Response token(@HeaderParam("Authorization") final String authorization, final Form form) {
        String restResponse = this.service.token(form);


        return Response.ok(restResponse).build();
    }


}
