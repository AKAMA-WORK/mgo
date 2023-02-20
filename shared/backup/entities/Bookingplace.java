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
@Table(name = "bookingplace")
public class Bookingplace extends PanacheEntityBase implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idbookingplace")
    private Integer idbookingplace;
    @Column(name = "place")
    private Integer place;
    @JoinColumn(name = "booking", referencedColumnName = "idbooking")
    @ManyToOne
    //@JsonIgnore
    private Booking booking;

    public Bookingplace() {
    }

    public Bookingplace(Integer idbookingplace) {
        this.idbookingplace = idbookingplace;
    }

    public Integer getIdbookingplace() {
        return idbookingplace;
    }

    public void setIdbookingplace(Integer idbookingplace) {
        this.idbookingplace = idbookingplace;
    }

    public Integer getPlace() {
        return place;
    }

    public void setPlace(Integer place) {
        this.place = place;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idbookingplace != null ? idbookingplace.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bookingplace)) {
            return false;
        }
        Bookingplace other = (Bookingplace) object;
        if ((this.idbookingplace == null && other.idbookingplace != null) || (this.idbookingplace != null && !this.idbookingplace.equals(other.idbookingplace))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "evoyage.Bookingplace[ idbookingplace=" + idbookingplace + " ]";
    }
    
}
