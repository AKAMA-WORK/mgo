/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mgo.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.*;


/**
 *
 * @author faraniarijaona
 */
@Entity
@Table(name = "vehicletype")
public class Vehicletype extends PanacheEntityBase implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idvehicletype")
    private Integer idvehicletype;
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "vehicletype")
    private Collection<Vehicle> vehicleCollection;

    public Vehicletype() {
    }

    public Vehicletype(Integer idvehicletype) {
        this.idvehicletype = idvehicletype;
    }

    public Integer getIdvehicletype() {
        return idvehicletype;
    }

    public void setIdvehicletype(Integer idvehicletype) {
        this.idvehicletype = idvehicletype;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Vehicle> getVehicleCollection() {
        return vehicleCollection;
    }

    public void setVehicleCollection(Collection<Vehicle> vehicleCollection) {
        this.vehicleCollection = vehicleCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idvehicletype != null ? idvehicletype.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vehicletype)) {
            return false;
        }
        Vehicletype other = (Vehicletype) object;
        if ((this.idvehicletype == null && other.idvehicletype != null) || (this.idvehicletype != null && !this.idvehicletype.equals(other.idvehicletype))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "evoyage.Vehicletype[ idvehicletype=" + idvehicletype + " ]";
    }
    
}
