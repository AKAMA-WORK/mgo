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
@Table(name = "agency")
public class Agency extends PanacheEntityBase implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idagency")
    private Integer idagency;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "agency")
    private Collection<Agencyemployee> agencyemployeeCollection;

    public Agency() {
    }

    public Agency(Integer idagency) {
        this.idagency = idagency;
    }

    public Agency(Integer idagency, String name) {
        this.idagency = idagency;
        this.name = name;
    }

    public Integer getIdagency() {
        return idagency;
    }

    public void setIdagency(Integer idagency) {
        this.idagency = idagency;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idagency != null ? idagency.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Agency)) {
            return false;
        }
        Agency other = (Agency) object;
        if ((this.idagency == null && other.idagency != null) || (this.idagency != null && !this.idagency.equals(other.idagency))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "evoyage.Agency[ idagency=" + idagency + " ]";
    }
    
}
