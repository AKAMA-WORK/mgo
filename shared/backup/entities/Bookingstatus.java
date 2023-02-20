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
@Table(name = "bookingstatus")
public class Bookingstatus extends PanacheEntityBase implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idbookingstatus")
    private Integer idbookingstatus;
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "bookingstatus")
    private Collection<Booking> bookingCollection;

    public Bookingstatus() {
    }

    public Bookingstatus(Integer idbookingstatus) {
        this.idbookingstatus = idbookingstatus;
    }

    public Integer getIdbookingstatus() {
        return idbookingstatus;
    }

    public void setIdbookingstatus(Integer idbookingstatus) {
        this.idbookingstatus = idbookingstatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Booking> getBookingCollection() {
        return bookingCollection;
    }

    public void setBookingCollection(Collection<Booking> bookingCollection) {
        this.bookingCollection = bookingCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idbookingstatus != null ? idbookingstatus.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bookingstatus)) {
            return false;
        }
        Bookingstatus other = (Bookingstatus) object;
        if ((this.idbookingstatus == null && other.idbookingstatus != null) || (this.idbookingstatus != null && !this.idbookingstatus.equals(other.idbookingstatus))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "evoyage.Bookingstatus[ idbookingstatus=" + idbookingstatus + " ]";
    }
    
}
