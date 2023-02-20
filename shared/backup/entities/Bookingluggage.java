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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author faraniarijaona
 */
@Entity
@Table(name = "bookingluggage")
public class Bookingluggage extends PanacheEntityBase implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idbookingluggage")
    private Integer idbookingluggage;
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Column(name = "weight")
    private Long weight;
    @Lob
    @Column(name = "description")
    private String description;
    @JoinColumn(name = "booking", referencedColumnName = "idbooking")
    @ManyToOne
    private Booking booking;

    public Bookingluggage() {
    }

    public Bookingluggage(Integer idbookingluggage) {
        this.idbookingluggage = idbookingluggage;
    }

    public Integer getIdbookingluggage() {
        return idbookingluggage;
    }

    public void setIdbookingluggage(Integer idbookingluggage) {
        this.idbookingluggage = idbookingluggage;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getWeight() {
        return weight;
    }

    public void setWeight(Long weight) {
        this.weight = weight;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        hash += (idbookingluggage != null ? idbookingluggage.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bookingluggage)) {
            return false;
        }
        Bookingluggage other = (Bookingluggage) object;
        if ((this.idbookingluggage == null && other.idbookingluggage != null) || (this.idbookingluggage != null && !this.idbookingluggage.equals(other.idbookingluggage))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "evoyage.Bookingluggage[ idbookingluggage=" + idbookingluggage + " ]";
    }
    
}
