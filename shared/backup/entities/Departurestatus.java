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
@Table(name = "departurestatus")
public class Departurestatus extends PanacheEntityBase implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iddeparturestatus")
    private Integer iddeparturestatus;
    @Column(name = "status")
    private String status;
    @OneToMany(mappedBy = "departurestatus")
    private Collection<Departure> departureCollection;

    public Departurestatus() {
    }

    public Departurestatus(Integer iddeparturestatus) {
        this.iddeparturestatus = iddeparturestatus;
    }

    public Integer getIddeparturestatus() {
        return iddeparturestatus;
    }

    public void setIddeparturestatus(Integer iddeparturestatus) {
        this.iddeparturestatus = iddeparturestatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Collection<Departure> getDepartureCollection() {
        return departureCollection;
    }

    public void setDepartureCollection(Collection<Departure> departureCollection) {
        this.departureCollection = departureCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddeparturestatus != null ? iddeparturestatus.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Departurestatus)) {
            return false;
        }
        Departurestatus other = (Departurestatus) object;
        if ((this.iddeparturestatus == null && other.iddeparturestatus != null) || (this.iddeparturestatus != null && !this.iddeparturestatus.equals(other.iddeparturestatus))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "evoyage.Departurestatus[ iddeparturestatus=" + iddeparturestatus + " ]";
    }
    
}
