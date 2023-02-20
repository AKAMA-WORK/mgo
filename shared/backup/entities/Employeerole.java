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
@Table(name = "employeerole")
public class Employeerole extends PanacheEntityBase implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idrole")
    private Integer idrole;
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "role")
    private Collection<Agencyemployee> agencyemployeeCollection;
    @OneToMany(mappedBy = "role")
    private Collection<Companyemployee> companyemployeeCollection;

    public Employeerole() {
    }

    public Employeerole(Integer idrole) {
        this.idrole = idrole;
    }

    public Integer getIdrole() {
        return idrole;
    }

    public void setIdrole(Integer idrole) {
        this.idrole = idrole;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Agencyemployee> getAgencyemployeeCollection() {
        return agencyemployeeCollection;
    }

    public void setAgencyemployeeCollection(Collection<Agencyemployee> agencyemployeeCollection) {
        this.agencyemployeeCollection = agencyemployeeCollection;
    }

    public Collection<Companyemployee> getCompanyemployeeCollection() {
        return companyemployeeCollection;
    }

    public void setCompanyemployeeCollection(Collection<Companyemployee> companyemployeeCollection) {
        this.companyemployeeCollection = companyemployeeCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idrole != null ? idrole.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Employeerole)) {
            return false;
        }
        Employeerole other = (Employeerole) object;
        if ((this.idrole == null && other.idrole != null) || (this.idrole != null && !this.idrole.equals(other.idrole))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "evoyage.Employeerole[ idrole=" + idrole + " ]";
    }
    
}
