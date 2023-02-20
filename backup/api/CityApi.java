package com.mgo.api;

import com.mgo.models.CityInfo;
import com.mgo.entities.*;
import com.mgo.services.UserService;
import io.quarkus.security.Authenticated;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import java.util.*;
import java.util.stream.Collectors;

@Path("/cities")
//@Authenticated
public class CityApi {
    @Context
    SecurityContext securityContext;

    @Inject
    UserService userService;

  

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CityInfo> cities() {

        
        return City.listAll().stream().map(c->{
            return new CityInfo((City) c);
        }).collect(Collectors.toList());
    }

}