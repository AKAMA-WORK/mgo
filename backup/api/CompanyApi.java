package com.mgo.api;

import com.mgo.models.CompanyInfo;
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

@Path("/companies")
//@Authenticated
public class CompanyApi {
    @Context
    SecurityContext securityContext;

    @Inject
    UserService userService;

  

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CompanyInfo> companies() {

        
        return Company.listAll().stream().map(c->{
            return new CompanyInfo((Company) c);
        }).collect(Collectors.toList());
    }

}