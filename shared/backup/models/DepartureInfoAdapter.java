package com.mgo.models;

import io.quarkus.qute.TemplateData;

@TemplateData
public class DepartureInfoAdapter {
    int id;
    String info;

    public DepartureInfoAdapter() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}