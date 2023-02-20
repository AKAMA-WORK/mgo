/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mgo.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author faraniarijaona
 */
@Entity
@Table(name = "bookingcancel")
public class Bookingcancel extends PanacheEntityBase implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idbookingcancel")
    private Integer idbookingcancel;
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Column(name = "amountreimbursed")
    private Long amountreimbursed;
    @Column(name = "paymentid")
    private String paymentid;
    @JoinColumn(name = "booking", referencedColumnName = "idbooking")
    @ManyToOne
    private Booking booking;
    @JoinColumn(name = "paymentmethod", referencedColumnName = "idpaymentmethod")
    @ManyToOne
    private Paymentmethod paymentmethod;

    public Bookingcancel() {
    }

    public Bookingcancel(Integer idbookingcancel) {
        this.idbookingcancel = idbookingcancel;
    }

    public Integer getIdbookingcancel() {
        return idbookingcancel;
    }

    public void setIdbookingcancel(Integer idbookingcancel) {
        this.idbookingcancel = idbookingcancel;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getAmountreimbursed() {
        return amountreimbursed;
    }

    public void setAmountreimbursed(Long amountreimbursed) {
        this.amountreimbursed = amountreimbursed;
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
        hash += (idbookingcancel != null ? idbookingcancel.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bookingcancel)) {
            return false;
        }
        Bookingcancel other = (Bookingcancel) object;
        if ((this.idbookingcancel == null && other.idbookingcancel != null) || (this.idbookingcancel != null && !this.idbookingcancel.equals(other.idbookingcancel))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "evoyage.Bookingcancel[ idbookingcancel=" + idbookingcancel + " ]";
    }
    
}
