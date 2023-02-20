/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mgo.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

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
@Table(name = "companyemployee")
public class Companyemployee extends PanacheEntityBase implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcompanyemployee")
    private Integer idcompanyemployee;
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
    @JoinColumn(name = "company", referencedColumnName = "idcompany")
    @ManyToOne
    private Company company;
    @JoinColumn(name = "role", referencedColumnName = "idrole")
    @ManyToOne
    private Employeerole role;

    @JoinColumn(name = "idcompanyagency", referencedColumnName = "idcompanyagency")
    @ManyToOne
    private Companyagency companyagency;


    public Companyemployee() {
    }

    public Companyemployee(Integer idcompanyemployee) {
        this.idcompanyemployee = idcompanyemployee;
    }

    public Integer getIdcompanyemployee() {
        return idcompanyemployee;
    }

    public void setIdcompanyemployee(Integer idcompanyemployee) {
        this.idcompanyemployee = idcompanyemployee;
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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Employeerole getRole() {
        return role;
    }

    public void setRole(Employeerole role) {
        this.role = role;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcompanyemployee != null ? idcompanyemployee.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Companyemployee)) {
            return false;
        }
        Companyemployee other = (Companyemployee) object;
        if ((this.idcompanyemployee == null && other.idcompanyemployee != null) || (this.idcompanyemployee != null && !this.idcompanyemployee.equals(other.idcompanyemployee))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "evoyage.Companyemployee[ idcompanyemployee=" + idcompanyemployee + " ]";
    }

    public Companyagency getCompanyagency() {
        return companyagency;
    }

    public void setCompanyagency(Companyagency companyagency) {
        this.companyagency = companyagency;
    }
 
    
}
