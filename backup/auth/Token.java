package com.mgo.auth;

import java.util.List;
import java.util.Set;

import com.mgo.model.PermissionModel;

public class Token {
   private  String accessToken;
   private String   tokenType;
   private String refreshToken;
   private long expiresIn;
   private  List<PermissionModel> permissions; 
   
public String getAccessToken() {
    return accessToken;
}
public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
}
public String getTokenType() {
    return tokenType;
}
public void setTokenType(String tokenType) {
    this.tokenType = tokenType;
}
public String getRefreshToken() {
    return refreshToken;
}
public void setRefreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
}
public long getExpiresIn() {
    return expiresIn;
}
public void setExpiresIn(long expiresIn) {
    this.expiresIn = expiresIn;
}

public List<PermissionModel> getPermissions() {
    return permissions;
}
public void setPermissions(List<PermissionModel> permissions) {
    this.permissions = permissions;
}

   
    
}
