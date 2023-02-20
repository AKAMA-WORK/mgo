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
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author faraniarijaona
 */
@Entity
@Table(name = "agencyemployee")
public class Agencyemployee extends PanacheEntityBase implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idemployee")
    private Integer idemployee;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "matricule")
    private String matricule;
    @Column(name = "name")
    private String name;
    @Column(name = "cin")
    private String cin;
    @Column(name = "contact")
    private String contact;
    @JoinColumn(name = "agency", referencedColumnName = "idagency")
    @ManyToOne
    private Agency agency;
    @JoinColumn(name = "role", referencedColumnName = "idrole")
    @ManyToOne
    private Employeerole role;
    @OneToMany(mappedBy = "driver")
    private Collection<Departure> departureCollection;
    @OneToMany(mappedBy = "driver2")
    private Collection<Departure> departureCollection1;

    public Agencyemployee() {
    }

    public Agencyemployee(Integer idemployee) {
        this.idemployee = idemployee;
    }

    public Integer getIdemployee() {
        return idemployee;
    }

    public void setIdemployee(Integer idemployee) {
        this.idemployee = idemployee;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Agency getAgency() {
        return agency;
    }

    public void setAgency(Agency agency) {
        this.agency = agency;
    }

    public Employeerole getRole() {
        return role;
    }

    public void setRole(Employeerole role) {
        this.role = role;
    }

    public Collection<Departure> getDepartureCollection() {
        return departureCollection;
    }

    public void setDepartureCollection(Collection<Departure> departureCollection) {
        this.departureCollection = departureCollection;
    }

    public Collection<Departure> getDepartureCollection1() {
        return departureCollection1;
    }

    public void setDepartureCollection1(Collection<Departure> departureCollection1) {
        this.departureCollection1 = departureCollection1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idemployee != null ? idemployee.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Agencyemployee)) {
            return false;
        }
        Agencyemployee other = (Agencyemployee) object;
        if ((this.idemployee == null && other.idemployee != null) || (this.idemployee != null && !this.idemployee.equals(other.idemployee))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "evoyage.Agencyemployee[ idemployee=" + idemployee + " ]";
    }
    
}
