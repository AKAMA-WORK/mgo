/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mgo.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

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
@Table(name = "client")
public class Client extends PanacheEntityBase implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idclient")
    private Integer idclient;
    @Column(name = "subscribedate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date subscribedate;
    @Column(name = "civility")
    private String civility;
    @Column(name = "fname")
    private String fname;
    @Column(name = "lname")
    private String lname;
    @Column(name = "phone")
    private Integer phone;
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
    @Column(name = "username")
    private String username;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "contactablebymail")
    private Short contactablebymail;
    @Column(name = "contactablebyphone")
    private Short contactablebyphone;
    @Column(name = "contactablebysms")
    private Short contactablebysms;
    @OneToMany(mappedBy = "client")
    private Collection<Booking> bookingCollection;
    @JoinColumn(name = "idtype", referencedColumnName = "ididtype")
    @ManyToOne
    private Idtype idtype;

    public Client() {
    }

    public Client(Integer idclient) {
        this.idclient = idclient;
    }

    public Integer getIdclient() {
        return idclient;
    }

    public void setIdclient(Integer idclient) {
        this.idclient = idclient;
    }

    public Date getSubscribedate() {
        return subscribedate;
    }

    public void setSubscribedate(Date subscribedate) {
        this.subscribedate = subscribedate;
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

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Short getContactablebymail() {
        return contactablebymail;
    }

    public void setContactablebymail(Short contactablebymail) {
        this.contactablebymail = contactablebymail;
    }

    public Short getContactablebyphone() {
        return contactablebyphone;
    }

    public void setContactablebyphone(Short contactablebyphone) {
        this.contactablebyphone = contactablebyphone;
    }

    public Short getContactablebysms() {
        return contactablebysms;
    }

    public void setContactablebysms(Short contactablebysms) {
        this.contactablebysms = contactablebysms;
    }

    public Collection<Booking> getBookingCollection() {
        return bookingCollection;
    }

    public void setBookingCollection(Collection<Booking> bookingCollection) {
        this.bookingCollection = bookingCollection;
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
        hash += (idclient != null ? idclient.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Client)) {
            return false;
        }
        Client other = (Client) object;
        if ((this.idclient == null && other.idclient != null) || (this.idclient != null && !this.idclient.equals(other.idclient))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "evoyage.Client[ idclient=" + idclient + " ]";
    }
    
}
