package com.mgo.models;

import io.quarkus.qute.TemplateData;

@TemplateData
public class PlaceInfo{
    int line;
    int column;
    int x;
    int y;
    int place;
    boolean isOccuped;

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public boolean isOccuped() {
        return isOccuped;
    }

    public void setOccuped(boolean occuped) {
        isOccuped = occuped;
    }
}