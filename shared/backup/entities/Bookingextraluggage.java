/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mgo.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.qute.TemplateData;

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
@Table(name = "bookingextraluggage")
public class Bookingextraluggage extends PanacheEntityBase implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idbookingextraluggage")
    private Integer idbookingextraluggage;
    @Column(name = "weight")
    private Long weight;
    @Column(name = "amount")
    private Long amount;
    @Column(name = "paymentid")
    private String paymentid;
    @JoinColumn(name = "booking", referencedColumnName = "idbooking")
    @ManyToOne
    private Booking booking;
    @JoinColumn(name = "paymentmethod", referencedColumnName = "idpaymentmethod")
    @ManyToOne
    private Paymentmethod paymentmethod;

    public Bookingextraluggage() {
    }

    public Bookingextraluggage(Integer idbookingextraluggage) {
        this.idbookingextraluggage = idbookingextraluggage;
    }

    public Integer getIdbookingextraluggage() {
        return idbookingextraluggage;
    }

    public void setIdbookingextraluggage(Integer idbookingextraluggage) {
        this.idbookingextraluggage = idbookingextraluggage;
    }

    public Long getWeight() {
        return weight;
    }

    public void setWeight(Long weight) {
        this.weight = weight;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getPaymentid() {
        return paymentid;
    }

    public void setPaymentid(String paymentid) {
        this.paymentid = paymentid;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public Paymentmethod getPaymentmethod() {
        return paymentmethod;
    }

    public void setPaymentmethod(Paymentmethod paymentmethod) {
        this.paymentmethod = paymentmethod;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idbookingextraluggage != null ? idbookingextraluggage.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bookingextraluggage)) {
            return false;
        }
        Bookingextraluggage other = (Bookingextraluggage) object;
        if ((this.idbookingextraluggage == null && other.idbookingextraluggage != null) || (this.idbookingextraluggage != null && !this.idbookingextraluggage.equals(other.idbookingextraluggage))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "evoyage.Bookingextraluggage[ idbookingextraluggage=" + idbookingextraluggage + " ]";
    }
    
}
