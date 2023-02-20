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
@Table(name = "vehicleplace")
public class Vehicleplace extends PanacheEntityBase implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idvehicleplace")
    private Integer idvehicleplace;
    @Column(name = "nbtotalplace")
    private Integer nbtotalplace;
    @Column(name = "nbligne")
    private Integer nbligne;
    @Column(name = "nbcolumn")
    private Integer nbcolumn;
    @OneToMany(mappedBy = "vehicleplace")
    private Collection<Vehicle> vehicleCollection;

    public Vehicleplace() {
    }

    public Vehicleplace(Integer idvehicleplace) {
        this.idvehicleplace = idvehicleplace;
    }

    public Integer getIdvehicleplace() {
        return idvehicleplace;
    }

    public void setIdvehicleplace(Integer idvehicleplace) {
        this.idvehicleplace = idvehicleplace;
    }

    public Integer getNbtotalplace() {
        return nbtotalplace;
    }

    public void setNbtotalplace(Integer nbtotalplace) {
        this.nbtotalplace = nbtotalplace;
    }

    public Integer getNbligne() {
        return nbligne;
    }

    public void setNbligne(Integer nbligne) {
        this.nbligne = nbligne;
    }

    public Integer getNbcolumn() {
        return nbcolumn;
    }

    public void setNbcolumn(Integer nbcolumn) {
        this.nbcolumn = nbcolumn;
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
        hash += (idvehicleplace != null ? idvehicleplace.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vehicleplace)) {
            return false;
        }
        Vehicleplace other = (Vehicleplace) object;
        if ((this.idvehicleplace == null && other.idvehicleplace != null) || (this.idvehicleplace != null && !this.idvehicleplace.equals(other.idvehicleplace))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "evoyage.Vehicleplace[ idvehicleplace=" + idvehicleplace + " ]";
    }
    
}
