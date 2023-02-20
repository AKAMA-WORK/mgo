package com.mgo.models;

import com.mgo.entities.City;


public class CityInfo{
    private Integer idCity;
    private String trigram;
    private String name;
    private Double longitude;
    private Double latitude;



    
    public CityInfo() {
    }

    public CityInfo(City city) {
        this.idCity = city.getIdcity();
        this.latitude = city.getLatitude();
        this.longitude = city.getLongitude();
        this.name=city.getName();
        this.trigram = city.getTrigram(); 
    }

    

    public Integer getIdCity() {
        return idCity;
    }

    public void setIdCity(Integer idCity) {
        this.idCity = idCity;
    }

    public String getTrigram() {
        return trigram;
    }
    public void setTrigram(String trigram) {
        this.trigram = trigram;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Double getLongitude() {
        return longitude;
    }
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
    public Double getLatitude() {
        return latitude;
    }
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }


    
}