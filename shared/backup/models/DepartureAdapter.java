package com.mgo.models;

import io.quarkus.qute.TemplateData;

@TemplateData
public class DepartureAdapter {
    private Integer id;
    private String dateDepart;
    private String heureDepart;
    private String category;
    private String vehicle;
    private String status;
    private String placeLibre;
    private String placeTotale;
    private String villeDestination;
    private String villeDepart;

    public DepartureAdapter() {
    }

    public String getStatus() {
        return status;
    }

    public String getPlaceTotale() {
        return placeTotale;
    }

    public void setPlaceTotale(String placeTotale) {
        this.placeTotale = placeTotale;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(String dateDepart) {
        this.dateDepart = dateDepart;
    }

    public String getHeureDepart() {
        return heureDepart;
    }

    public void setHeureDepart(String heureDepart) {
        this.heureDepart = heureDepart;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public String getPlaceLibre() {
        return placeLibre;
    }

    public void setPlaceLibre(String placeLibre) {
        this.placeLibre = placeLibre;
    }

    public void setVilleDestination(String villeDestination) {
        this.villeDestination = villeDestination;
    }

    public String getVilleDestination() {
        return villeDestination;
    }

    public void setVilleDepart(String villeDepart) {
        this.villeDepart = villeDepart;
    }

    public String getVilleDepart() {
        return villeDepart;
    }
}
