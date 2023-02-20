package com.mgo.query;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(name = "StringFieldComparisons")
public class StringFieldComparisons extends CommonFieldComparisonType<String> {
    String like;
    String notLike;
    String iLike;
    String notILike;

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }

    public String getNotLike() {
        return notLike;
    }

    public void setNotLike(String notLike) {
        this.notLike = notLike;
    }

    public String getiLike() {
        return iLike;
    }

    public void setiLike(String iLike) {
        this.iLike = iLike;
    }

    public String getNotILike() {
        return notILike;
    }

    public void setNotILike(String notILike) {
        this.notILike = notILike;
    }
}
