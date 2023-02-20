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
@Table(name = "company")
public class Company extends PanacheEntityBase implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcompany")
    private Integer idcompany;
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "transferedfrom")
    private Collection<Booking> bookingCollection;
    @OneToMany(mappedBy = "company")
    private Collection<Companyconfig> companyconfigCollection;
    @OneToMany(mappedBy = "company")
    private Collection<Companyemployee> companyemployeeCollection;
    @OneToMany(mappedBy = "company")
    private Collection<Departure> departureCollection;

    public Company() {
    }

    public Company(Integer idcompany) {
        this.idcompany = idcompany;
    }

    public Integer getIdcompany() {
        return idcompany;
    }

    public void setIdcompany(Integer idcompany) {
        this.idcompany = idcompany;
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

    public Collection<Companyconfig> getCompanyconfigCollection() {
        return companyconfigCollection;
    }

    public void setCompanyconfigCollection(Collection<Companyconfig> companyconfigCollection) {
        this.companyconfigCollection = companyconfigCollection;
    }

    public Collection<Companyemployee> getCompanyemployeeCollection() {
        return companyemployeeCollection;
    }

    public void setCompanyemployeeCollection(Collection<Companyemployee> companyemployeeCollection) {
        this.companyemployeeCollection = companyemployeeCollection;
    }

    public Collection<Departure> getDepartureCollection() {
        return departureCollection;
    }

    public void setDepartureCollection(Collection<Departure> departureCollection) {
        this.departureCollection = departureCollection;
    }


    

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcompany != null ? idcompany.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Company)) {
            return false;
        }
        Company other = (Company) object;
        if ((this.idcompany == null && other.idcompany != null) || (this.idcompany != null && !this.idcompany.equals(other.idcompany))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "evoyage.Company[ idcompany=" + idcompany + " ]";
    }
    
}
