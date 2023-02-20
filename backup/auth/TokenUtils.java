package com.mgo.auth;

import io.smallrye.jwt.build.Jwt;
import org.eclipse.microprofile.jwt.Claims;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.mgo.model.PermissionModel;

/**
 * Utilities for generating a JWT for testing
 */
public class TokenUtils {
    private TokenUtils() {
        // no-op: utility class
    }

    public static Token generateTokenString(final String username, final PermissionModel ...groups) {
        return generateTokenString(username, List.of(groups));
    }

    public static Token generateTokenString(final String username, final List<PermissionModel> groups) {
        long currentTimeInSecs = currentTimeInSecs();

        long expiresIn = 1800;
        String accessToken =  Jwt.claims()
                  .issuer("https://api.m-go.voyage")
                  .issuedAt(currentTimeInSecs())
                  .claim(Claims.auth_time.name(), currentTimeInSecs)
                  .expiresAt(currentTimeInSecs + expiresIn)
                  .upn(username)
                  .preferredUserName(username)
                  .groups(groups.stream().map(p->p.getName()).collect(Collectors.toSet()))
                  .sign();

     
         
      Token token = new Token();
      token.setExpiresIn(expiresIn);
      token.setAccessToken(accessToken);
      token.setTokenType("bearer");
      token.setRefreshToken( getRefreshToken(currentTimeInSecs, username, groups));
      token.setPermissions(groups);

      return token;
    }

    private static String getRefreshToken(
        long currentTimeInSecs, 
     final String username, 
    final List<PermissionModel> groups
    ){
        long expiresIn = 3600 * 24; // one day


        return Jwt.claims()
        .issuer("https://api.m-go.voyage")
        .issuedAt(currentTimeInSecs())
        .claim(Claims.auth_time.name(), currentTimeInSecs)
        .expiresAt(currentTimeInSecs + expiresIn)
        .upn(username)
        .preferredUserName(username)
        .groups(groups.stream().map(p->p.getName()).collect(Collectors.toSet()))
        .sign();


    }

    public static int currentTimeInSecs() {
        long currentTimeMS = System.currentTimeMillis();
        return (int) (currentTimeMS / 1000);
    }
}
