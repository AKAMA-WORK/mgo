package com.mgo.model.city;


import com.mgo.entity.City;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
@Schema(name = "City")
public class CityModel {
    @Schema(name = "cityId", description = "The id of city")
     String cityId;


    @Schema(name = "zipCode", description = "The zip code", example = "101")
    Integer zipCode;

    @Schema(name = "trigram", description = "The trigram of city", example = "ANT")
     String trigram;

    @Schema(name = "name", description = "The name of city", example = "ANTANANARIVO")
     String name;

    @Schema(name = "longitude", description = "The longitude")

     Double longitude;

    @Schema(name = "longitude", description = "The latitude")

    private Double latitude;

    public CityModel() {
    }

    public CityModel(City city) {
        this.cityId = city.getCityId();
        this.latitude = city.getLatitude();
        this.longitude = city.getLongitude();
        this.name = city.getName();
        this.trigram = city.getTrigram();
    }


    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
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