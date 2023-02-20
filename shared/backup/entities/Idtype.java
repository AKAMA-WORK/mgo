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
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author faraniarijaona
 */
@Entity
@Table(name = "idtype")
public class Idtype extends PanacheEntityBase implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ididtype")
    private Integer ididtype;
    @Column(name = "idtypecol")
    private String idtypecol;
    @OneToMany(mappedBy = "idtype")
    private Collection<Departuremanifold> departuremanifoldCollection;
    @OneToMany(mappedBy = "idtype")
    private Collection<Client> clientCollection;

    public Idtype() {
    }

    public Idtype(Integer ididtype) {
        this.ididtype = ididtype;
    }

    public Integer getIdidtype() {
        return ididtype;
    }

    public void setIdidtype(Integer ididtype) {
        this.ididtype = ididtype;
    }

    public String getIdtypecol() {
        return idtypecol;
    }

    public void setIdtypecol(String idtypecol) {
        this.idtypecol = idtypecol;
    }

    public Collection<Departuremanifold> getDeparturemanifoldCollection() {
        return departuremanifoldCollection;
    }

    public void setDeparturemanifoldCollection(Collection<Departuremanifold> departuremanifoldCollection) {
        this.departuremanifoldCollection = departuremanifoldCollection;
    }

    public Collection<Client> getClientCollection() {
        return clientCollection;
    }

    public void setClientCollection(Collection<Client> clientCollection) {
        this.clientCollection = clientCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ididtype != null ? ididtype.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Idtype)) {
            return false;
        }
        Idtype other = (Idtype) object;
        if ((this.ididtype == null && other.ididtype != null) || (this.ididtype != null && !this.ididtype.equals(other.ididtype))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "evoyage.Idtype[ ididtype=" + ididtype + " ]";
    }
    
}
