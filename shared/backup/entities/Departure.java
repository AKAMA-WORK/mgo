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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name = "departure")
public class Departure extends PanacheEntityBase implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iddeparture")
    private Integer iddeparture;
    @Column(name = "dateheure")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateheure;
    @Column(name = "carmatricule")
    private String carmatricule;
    @Column(name = "price")
    private Integer price;
    @OneToMany(mappedBy = "departure")
    @LazyCollection(LazyCollectionOption.FALSE)
    private Collection<Booking> bookingCollection;
    @OneToMany(mappedBy = "departure")
    private Collection<Departuremanifold> departuremanifoldCollection;
    @JoinColumn(name = "driver", referencedColumnName = "idcompanyemployee")
    @ManyToOne
    private Companyemployee driver;
    @JoinColumn(name = "driver2", referencedColumnName = "idcompanyemployee")
    @ManyToOne
    private Companyemployee driver2;
    @JoinColumn(name = "category", referencedColumnName = "idcategory")
    @ManyToOne
    private Category category;
    @JoinColumn(name = "destination", referencedColumnName = "idcity")
    @ManyToOne
    private City destination;
    @JoinColumn(name = "startin", referencedColumnName = "idcity")
    @ManyToOne
    private City startin;
    @JoinColumn(name = "company", referencedColumnName = "idcompany")
    @ManyToOne
    private Company company;
    
    @JoinColumn(name = "idcompanyagency", referencedColumnName = "idcompanyagency")
    @ManyToOne
    private Companyagency companyagency;

    @JoinColumn(name = "departurestatus", referencedColumnName = "iddeparturestatus")
    @ManyToOne
    private Departurestatus departurestatus;
    @JoinColumn(name = "vehicle", referencedColumnName = "idvehicle")
    @ManyToOne
    private Vehicle vehicle;

    @Column(name = "lockuntil")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lockuntil;

    public Departure() {
    }

    public Departure(Integer iddeparture) {
        this.iddeparture = iddeparture;
    }

    public Integer getIddeparture() {
        return iddeparture;
    }

    public void setIddeparture(Integer iddeparture) {
        this.iddeparture = iddeparture;
    }

    public Date getDateheure() {
        return dateheure;
    }

    public void setDateheure(Date dateheure) {
        this.dateheure = dateheure;
    }

    public String getCarmatricule() {
        return carmatricule;
    }

    public void setCarmatricule(String carmatricule) {
        this.carmatricule = carmatricule;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Collection<Booking> getBookingCollection() {
        return bookingCollection;
    }

    public void setBookingCollection(Collection<Booking> bookingCollection) {
        this.bookingCollection = bookingCollection;
    }

    public Collection<Departuremanifold> getDeparturemanifoldCollection() {
        return departuremanifoldCollection;
    }

    public void setDeparturemanifoldCollection(Collection<Departuremanifold> departuremanifoldCollection) {
        this.departuremanifoldCollection = departuremanifoldCollection;
    }

    public Companyemployee getDriver() {
        return driver;
    }

    public void setDriver(Companyemployee driver) {
        this.driver = driver;
    }

    public Companyemployee getDriver2() {
        return driver2;
    }

    public void setDriver2(Companyemployee driver2) {
        this.driver2 = driver2;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public City getDestination() {
        return destination;
    }

    public void setDestination(City destination) {
        this.destination = destination;
    }

    public City getStartin() {
        return startin;
    }

    public void setStartin(City startin) {
        this.startin = startin;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Departurestatus getDeparturestatus() {
        return departurestatus;
    }

    public void setDeparturestatus(Departurestatus departurestatus) {
        this.departurestatus = departurestatus;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Companyagency getCompanyagency() {
        return companyagency;
    }

    public void setCompanyagency(Companyagency companyagency) {
        this.companyagency = companyagency;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddeparture != null ? iddeparture.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Departure)) {
            return false;
        }
        Departure other = (Departure) object;
        if ((this.iddeparture == null && other.iddeparture != null) || (this.iddeparture != null && !this.iddeparture.equals(other.iddeparture))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "evoyage.Departure[ iddeparture=" + iddeparture + " ]";
    }
    
}
