package com.mgo.auth;

import com.mgo.entity.Person;

public class AuthenticatedUser {
    private String userId;
    private Person person;

    public AuthenticatedUser(String userId, Person person) {
        this.userId = userId;
        this.person = person;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
