package com.mgo.auth;

import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Metered;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.opentracing.Traced;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import com.mgo.tracer.TraceLog;

import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.mgo.entities.Permission;
import com.mgo.entities.Role;
import com.mgo.entities.User;
import com.mgo.model.PermissionModel;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Path("/oauth/token")
public class TokenEndpoint {
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
        final BasicAuthCredentials credentials = BasicAuthCredentials.createCredentialsFromHeader(authorization);

       User user =  User.find("username='"+credentials.getUsername()+"'").firstResult();

  
       if(user==null || !user.getPassword().equals(credentials.getPassword())){
           return Response.status(Status.UNAUTHORIZED).build(); 
       }



        final Token token = TokenUtils.generateTokenString(credentials.getUsername(), getUserGroups(user));
        return Response.ok(
              new JsonObject()
        .put("access_token", token.getAccessToken())
        .put("token_type", token.getTokenType())
        .put("expires_in", token.getExpiresIn())
        .put("permissions", new JsonArray(token.getPermissions()))
        .encode()).build();
    }


    private List<PermissionModel> getUserGroups(User user) {

        Set<Permission> userPermissions = user.getPermissions();

        if(userPermissions.size()>0){
            return userPermissions.stream().map(p->new PermissionModel(p)).collect(Collectors.toList());
        }

        Role role = user.getRole();

        if(role!=null){

            return role.getPermissions().stream().map(p->new PermissionModel(p)).collect(Collectors.toList());
        }

        return Collections.emptyList();

    }
    

    /*private Set<String> getGroups(final Form form) {
        final MultivaluedMap<String, String> parameters = form.asMap();
        final String scope = parameters.getFirst("scope");
        if (scope == null) {
            return new HashSet<>();
        }
        return Stream.of(scope.split(" ")).collect(Collectors.toSet());
    }*/
}
