package com.mgo.auth;

import com.mgo.entity.Person;
import io.quarkus.panache.common.Parameters;
import io.smallrye.jwt.auth.principal.JWTCallerPrincipal;

import javax.ws.rs.core.SecurityContext;

public class AuthService {
    public static AuthenticatedUser getAuthenticatedUser(
            SecurityContext securityContext
    ){

        if(securityContext!=null && securityContext.getUserPrincipal()!=null){
             String userId = ((JWTCallerPrincipal) securityContext.getUserPrincipal()).getSubject();

             if(userId==null){
                 return  null;
             }

            Person person =  Person.find("userId=:userId", Parameters.with("userId",userId)).firstResult();

            return new AuthenticatedUser(userId,person);
        }


        return  null;
    }
}
