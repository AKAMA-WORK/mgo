/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mgo.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import java.io.Serializable;
import javax.persistence.*;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author faraniarijaona
 */
@Entity
@Table(name = "vehiclemappingplace")
public class Vehiclemappingplace extends PanacheEntityBase implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idvehiclemappingplace")
    private Integer idvehiclemappingplace;
    @Column(name = "x")
    private Integer x;
    @Column(name = "y")
    private Integer y;
    @Column(name = "placenumber")
    private Integer placenumber;
    @JoinColumn(name = "vehicle", referencedColumnName = "idvehicle")
    @ManyToOne
    private Vehicle vehicle;

    public Vehiclemappingplace() {
    }

    public Vehiclemappingplace(Integer idvehiclemappingplace) {
        this.idvehiclemappingplace = idvehiclemappingplace;
    }

    public Integer getIdvehiclemappingplace() {
        return idvehiclemappingplace;
    }

    public void setIdvehiclemappingplace(Integer idvehiclemappingplace) {
        this.idvehiclemappingplace = idvehiclemappingplace;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Integer getPlacenumber() {
        return placenumber;
    }

    public void setPlacenumber(Integer placenumber) {
        this.placenumber = placenumber;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idvehiclemappingplace != null ? idvehiclemappingplace.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vehiclemappingplace)) {
            return false;
        }
        Vehiclemappingplace other = (Vehiclemappingplace) object;
        if ((this.idvehiclemappingplace == null && other.idvehiclemappingplace != null) || (this.idvehiclemappingplace != null && !this.idvehiclemappingplace.equals(other.idvehiclemappingplace))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "evoyage.Vehiclemappingplace[ idvehiclemappingplace=" + idvehiclemappingplace + " ]";
    }
    
}
