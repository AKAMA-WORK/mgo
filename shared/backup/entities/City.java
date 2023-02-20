/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mgo.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

/**
 *
 * @author faraniarijaona
 */
@Entity
@Table(name = "city")
public class City extends PanacheEntityBase implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcity")
    private Integer idcity;
    @Basic(optional = false)
    @Column(name = "trigram")
    private String trigram;
    @Column(name = "name")
    private String name;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "longitude")
    private Double longitude;
    @Column(name = "latitude")
    private Double latitude;
    @OneToMany(mappedBy = "destination")
    private Collection<Departure> departureCollection;
    @OneToMany(mappedBy = "startin")
    private Collection<Departure> departureCollection1;

    public City() {
    }

    public City(Integer idcity) {
        this.idcity = idcity;
    }

    public City(Integer idcity, String trigram) {
        this.idcity = idcity;
        this.trigram = trigram;
    }

    public Integer getIdcity() {
        return idcity;
    }

    public void setIdcity(Integer idcity) {
        this.idcity = idcity;
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

    public Collection<Departure> getDepartureCollection() {
        return departureCollection;
    }

    public void setDepartureCollection(Collection<Departure> departureCollection) {
        this.departureCollection = departureCollection;
    }

    public Collection<Departure> getDepartureCollection1() {
        return departureCollection1;
    }

    public void setDepartureCollection1(Collection<Departure> departureCollection1) {
        this.departureCollection1 = departureCollection1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcity != null ? idcity.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof City)) {
            return false;
        }
        City other = (City) object;
        if ((this.idcity == null && other.idcity != null) || (this.idcity != null && !this.idcity.equals(other.idcity))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "evoyage.City[ idcity=" + idcity + " ]";
    }
    
}
