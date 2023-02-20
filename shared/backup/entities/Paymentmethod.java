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
@Table(name = "paymentmethod")
public class Paymentmethod extends PanacheEntityBase implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idpaymentmethod")
    private Integer idpaymentmethod;
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "paymentmethod")
    private Collection<Booking> bookingCollection;
    @OneToMany(mappedBy = "paymentmethod")
    private Collection<Bookingextraluggage> bookingextraluggageCollection;
    @OneToMany(mappedBy = "paymentmethod")
    private Collection<Bookingcancel> bookingcancelCollection;

    public Paymentmethod() {
    }

    public Paymentmethod(Integer idpaymentmethod) {
        this.idpaymentmethod = idpaymentmethod;
    }

    public Integer getIdpaymentmethod() {
        return idpaymentmethod;
    }

    public void setIdpaymentmethod(Integer idpaymentmethod) {
        this.idpaymentmethod = idpaymentmethod;
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

    public Collection<Bookingextraluggage> getBookingextraluggageCollection() {
        return bookingextraluggageCollection;
    }

    public void setBookingextraluggageCollection(Collection<Bookingextraluggage> bookingextraluggageCollection) {
        this.bookingextraluggageCollection = bookingextraluggageCollection;
    }

    public Collection<Bookingcancel> getBookingcancelCollection() {
        return bookingcancelCollection;
    }

    public void setBookingcancelCollection(Collection<Bookingcancel> bookingcancelCollection) {
        this.bookingcancelCollection = bookingcancelCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpaymentmethod != null ? idpaymentmethod.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Paymentmethod)) {
            return false;
        }
        Paymentmethod other = (Paymentmethod) object;
        if ((this.idpaymentmethod == null && other.idpaymentmethod != null) || (this.idpaymentmethod != null && !this.idpaymentmethod.equals(other.idpaymentmethod))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "evoyage.Paymentmethod[ idpaymentmethod=" + idpaymentmethod + " ]";
    }
    
}
