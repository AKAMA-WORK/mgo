package com.mgo.auth;

import io.quarkus.runtime.StartupEvent;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.keycloak.admin.client.Keycloak;

import javax.enterprise.event.Observes;

public class KeycloakAuth {
    @ConfigProperty(name = "keycloak.auth-server-url")
    String authServerUrl;

    @ConfigProperty(name = "keycloak.realm")
    String realm;

    @ConfigProperty(name = "keycloak.username")
    String username;

    @ConfigProperty(name = "keycloak.password")
    String password;

    @ConfigProperty(name = "keycloak.client-id")
    String clientId;

    @ConfigProperty(name = "keycloak.client-secret")
    String clientSecret;



    private Keycloak keycloak;



    public  void startup(@Observes StartupEvent event){
  /*          this.keycloak = Keycloak.getInstance(
                    this.authServerUrl,
                    this.realm,
                    this.username,
                    this.password,  this.clientId, this.clientSecret);
*/
    }


    public Keycloak getKeycloak() {
        return keycloak;
    }
}
