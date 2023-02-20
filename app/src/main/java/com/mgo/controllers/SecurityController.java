package com.mgo.controllers;

import java.net.http.HttpClient;
import java.time.Duration;

import javax.ws.rs.Path;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.eclipse.microprofile.config.inject.ConfigProperty;


// https://golb.hplar.ch/2020/05/hcaptcha.html
@Path("/security")
public class SecurityController {

    @ConfigProperty(name = "hcaptcha.secret")
    String hcaptchaSecret;

    @ConfigProperty(name = "hcaptcha.site-key")
    String hcaptchaSiteKey;

    private final HttpClient httpClient;
    private final ObjectMapper om = new ObjectMapper();

    public SecurityController() {
        this.httpClient = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(5))
        .build();
    }


    



}
