/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mgo.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.*;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author faraniarijaona
 */
@Entity
@Table(name = "booking")
public class Booking extends PanacheEntityBase implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idbooking")
    private Integer idbooking;
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Column(name = "datepointage")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datepointage;
    @Column(name = "amount")
    private Integer amount;
    @Column(name = "paymentid")
    private String paymentid;
    @Column(name = "confirmdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date confirmdate;
    @Column(name = "canceldate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date canceldate;
    @Lob
    @Column(name = "description")
    private String description;
    @Column(name = "authorizedluggage")
    private Long authorizedluggage;
    @JoinColumn(name = "bookingstatus", referencedColumnName = "idbookingstatus")
    @ManyToOne
    private Bookingstatus bookingstatus;
    @JoinColumn(name = "client", referencedColumnName = "idclient")
    @ManyToOne
    private Client client;
    @JoinColumn(name = "transferedfrom", referencedColumnName = "idcompany")
    @ManyToOne
    private Company transferedfrom;
    @JoinColumn(name = "departure", referencedColumnName = "iddeparture")
    @ManyToOne
    private Departure departure;
    @JoinColumn(name = "paymentmethod", referencedColumnName = "idpaymentmethod")
    @ManyToOne
    private Paymentmethod paymentmethod;
    @OneToMany(mappedBy = "booking")
    @LazyCollection(LazyCollectionOption.FALSE)
    private Collection<Bookingplace> bookingplaceCollection;
    @OneToMany(mappedBy = "booking")
    @LazyCollection(LazyCollectionOption.FALSE)
    private Collection<Bookingextraluggage> bookingextraluggageCollection;
    @OneToMany(mappedBy = "booking")
    @LazyCollection(LazyCollectionOption.FALSE)
    private Collection<Bookingluggage> bookingluggageCollection;
    @OneToMany(mappedBy = "booking")
    @LazyCollection(LazyCollectionOption.FALSE)
    private Collection<Bookingcancel> bookingcancelCollection;
    @Column(name = "boarding")
    private Boolean boarding;
    @Column(name = "boarding_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date boardingDate;

    public Booking() {
    }

    public Booking(Integer idbooking) {
        this.idbooking = idbooking;
    }

    public Integer getIdbooking() {
        return idbooking;
    }

    public void setIdbooking(Integer idbooking) {
        this.idbooking = idbooking;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDatepointage() {
        return datepointage;
    }

    public void setDatepointage(Date datepointage) {
        this.datepointage = datepointage;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getPaymentid() {
        return paymentid;
    }

    public void setPaymentid(String paymentid) {
        this.paymentid = paymentid;
    }

    public Date getConfirmdate() {
        return confirmdate;
    }

    public void setConfirmdate(Date confirmdate) {
        this.confirmdate = confirmdate;
    }

    public Date getCanceldate() {
        return canceldate;
    }

    public void setCanceldate(Date canceldate) {
        this.canceldate = canceldate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getAuthorizedluggage() {
        return authorizedluggage;
    }

    public void setAuthorizedluggage(Long authorizedluggage) {
        this.authorizedluggage = authorizedluggage;
    }

    public Bookingstatus getBookingstatus() {
        return bookingstatus;
    }

    public void setBookingstatus(Bookingstatus bookingstatus) {
        this.bookingstatus = bookingstatus;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Company getTransferedfrom() {
        return transferedfrom;
    }

    public void setTransferedfrom(Company transferedfrom) {
        this.transferedfrom = transferedfrom;
    }

    public Departure getDeparture() {
        return departure;
    }

    public void setDeparture(Departure departure) {
        this.departure = departure;
    }

    public Paymentmethod getPaymentmethod() {
        return paymentmethod;
    }

    public void setPaymentmethod(Paymentmethod paymentmethod) {
        this.paymentmethod = paymentmethod;
    }

    public Collection<Bookingplace> getBookingplaceCollection() {
        return bookingplaceCollection;
    }

    public void setBookingplaceCollection(Collection<Bookingplace> bookingplaceCollection) {
        this.bookingplaceCollection = bookingplaceCollection;
    }

    public Collection<Bookingextraluggage> getBookingextraluggageCollection() {
        return bookingextraluggageCollection;
    }

    public void setBookingextraluggageCollection(Collection<Bookingextraluggage> bookingextraluggageCollection) {
        this.bookingextraluggageCollection = bookingextraluggageCollection;
    }

    public Collection<Bookingluggage> getBookingluggageCollection() {
        return bookingluggageCollection;
    }

    public void setBookingluggageCollection(Collection<Bookingluggage> bookingluggageCollection) {
        this.bookingluggageCollection = bookingluggageCollection;
    }

    public Collection<Bookingcancel> getBookingcancelCollection() {
        return bookingcancelCollection;
    }

    public void setBookingcancelCollection(Collection<Bookingcancel> bookingcancelCollection) {
        this.bookingcancelCollection = bookingcancelCollection;
    }

    public Boolean getBoarding() {
        return boarding;
    }

    public void setBoarding(Boolean boarding) {
        this.boarding = boarding;
    }

    public Date getBoardingDate() {
        return boardingDate;
    }

    public void setBoardingDate(Date boardingDate) {
        this.boardingDate = boardingDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idbooking != null ? idbooking.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Booking)) {
            return false;
        }
        Booking other = (Booking) object;
        if ((this.idbooking == null && other.idbooking != null) || (this.idbooking != null && !this.idbooking.equals(other.idbooking))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "evoyage.Booking[ idbooking=" + idbooking + " ]";
    }
    
}
