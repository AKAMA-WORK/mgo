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
@Table(name = "departuremanifold")
public class Departuremanifold extends PanacheEntityBase implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "iddeparturemanifold")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer iddeparturemanifold;
    @Column(name = "placenumber")
    private Integer placenumber;
    @Column(name = "civility")
    private String civility;
    @Column(name = "fname")
    private String fname;
    @Column(name = "lname")
    private String lname;
    @Column(name = "address")
    private String address;
    @Column(name = "idnumber")
    private String idnumber;
    @Column(name = "idissuedate")
    @Temporal(TemporalType.DATE)
    private Date idissuedate;
    @Column(name = "idissuelocation")
    private String idissuelocation;
    @Column(name = "idduplicatadate")
    @Temporal(TemporalType.DATE)
    private Date idduplicatadate;
    @Column(name = "idduplicatalocation")
    private String idduplicatalocation;
    @Column(name = "contactcivility")
    private String contactcivility;
    @Column(name = "contactfname")
    private String contactfname;
    @Column(name = "contactlname")
    private String contactlname;
    @Column(name = "contactaddress")
    private String contactaddress;
    @Column(name = "contactphone")
    private String contactphone;
    @Column(name = "contactphone1")
    private String contactphone1;
    @Column(name = "daty")
    private String daty;
    @JoinColumn(name = "departure", referencedColumnName = "iddeparture")
    @ManyToOne
    private Departure departure;
    @JoinColumn(name = "idtype", referencedColumnName = "ididtype")
    @ManyToOne
    private Idtype idtype;

    public Departuremanifold() {
    }

    public Departuremanifold(Integer iddeparturemanifold) {
        this.iddeparturemanifold = iddeparturemanifold;
    }

    public Integer getIddeparturemanifold() {
        return iddeparturemanifold;
    }

    public void setIddeparturemanifold(Integer iddeparturemanifold) {
        this.iddeparturemanifold = iddeparturemanifold;
    }

    public Integer getPlacenumber() {
        return placenumber;
    }

    public void setPlacenumber(Integer placenumber) {
        this.placenumber = placenumber;
    }

    public String getCivility() {
        return civility;
    }

    public void setCivility(String civility) {
        this.civility = civility;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIdnumber() {
        return idnumber;
    }

    public void setIdnumber(String idnumber) {
        this.idnumber = idnumber;
    }

    public Date getIdissuedate() {
        return idissuedate;
    }

    public void setIdissuedate(Date idissuedate) {
        this.idissuedate = idissuedate;
    }

    public String getIdissuelocation() {
        return idissuelocation;
    }

    public void setIdissuelocation(String idissuelocation) {
        this.idissuelocation = idissuelocation;
    }

    public Date getIdduplicatadate() {
        return idduplicatadate;
    }

    public void setIdduplicatadate(Date idduplicatadate) {
        this.idduplicatadate = idduplicatadate;
    }

    public String getIdduplicatalocation() {
        return idduplicatalocation;
    }

    public void setIdduplicatalocation(String idduplicatalocation) {
        this.idduplicatalocation = idduplicatalocation;
    }

    public String getContactcivility() {
        return contactcivility;
    }

    public void setContactcivility(String contactcivility) {
        this.contactcivility = contactcivility;
    }

    public String getContactfname() {
        return contactfname;
    }

    public void setContactfname(String contactfname) {
        this.contactfname = contactfname;
    }

    public String getContactlname() {
        return contactlname;
    }

    public void setContactlname(String contactlname) {
        this.contactlname = contactlname;
    }

    public String getContactaddress() {
        return contactaddress;
    }

    public void setContactaddress(String contactaddress) {
        this.contactaddress = contactaddress;
    }

    public String getContactphone() {
        return contactphone;
    }

    public void setContactphone(String contactphone) {
        this.contactphone = contactphone;
    }

    public String getContactphone1() {
        return contactphone1;
    }

    public void setContactphone1(String contactphone1) {
        this.contactphone1 = contactphone1;
    }

    public String getDaty() {
        return daty;
    }

    public void setDaty(String daty) {
        this.daty = daty;
    }

    public Departure getDeparture() {
        return departure;
    }

    public void setDeparture(Departure departure) {
        this.departure = departure;
    }

    public Idtype getIdtype() {
        return idtype;
    }

    public void setIdtype(Idtype idtype) {
        this.idtype = idtype;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddeparturemanifold != null ? iddeparturemanifold.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Departuremanifold)) {
            return false;
        }
        Departuremanifold other = (Departuremanifold) object;
        if ((this.iddeparturemanifold == null && other.iddeparturemanifold != null) || (this.iddeparturemanifold != null && !this.iddeparturemanifold.equals(other.iddeparturemanifold))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "evoyage.Departuremanifold[ iddeparturemanifold=" + iddeparturemanifold + " ]";
    }
    
}
