package com.mgo.auth;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Form;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/")
@RegisterRestClient(configKey = "keycloak")
public interface KeyCloakService {

    @Path("/protocol/openid-connect/token")
    @POST
    String token(Form form);


}
