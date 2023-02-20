package com.mgo.resource;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(staticName = "ErrorPayload")
@Getter
public class ErrorPayload {
    private String code;
    private String message;
}
