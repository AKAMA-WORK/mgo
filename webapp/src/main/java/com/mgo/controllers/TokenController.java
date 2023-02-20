package com.mgo.controllers;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import io.quarkus.security.Authenticated;
import io.quarkus.oidc.IdToken;
import io.quarkus.oidc.RefreshToken;
import io.quarkus.panache.common.Sort;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import org.eclipse.microprofile.jwt.JsonWebToken;

import io.quarkus.qute.Location;

@Path("/home.html")
@Authenticated
public class TokenController {

        /**
         * Injection point for the ID Token issued by the OpenID Connect Provider
         */
        @Inject
        @IdToken
        JsonWebToken idToken;

        /**
         * Injection point for the Access Token issued by the OpenID Connect Provider
         */
        @Inject
        JsonWebToken accessToken;

        /**
         * Injection point for the Refresh Token issued by the OpenID Connect Provider
         */
        @Inject
        RefreshToken refreshToken;

        /**
         * Returns the tokens available to the application. This endpoint exists only
         * for demonstration purposes, you should not
         * expose these tokens in a real application.
         *
         * @return a HTML page containing the tokens available to the application
         */

        @Location("home.qute.html")
        Template homeTemplate;

        @GET
        @Produces(MediaType.TEXT_HTML)
        public TemplateInstance home() throws ParseException {
                return homeTemplate.data("title", "Test")
                .data("username",this.idToken.claim("preferred_username"))
                .data("scopes",this.accessToken.claim("scope"))
                .data("refresh_token",this.refreshToken.getToken());
        }
}
