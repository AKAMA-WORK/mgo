/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mgo.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import java.io.Serializable;

/**
 *
 * @author ginot.antsanirina
 */
@Entity
@Table(name = "permission")
public class Permission extends PanacheEntityBase implements Serializable {

    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    @Column(name = "idpermission")
    @Id
    private String idpermission;

    @Column(name = "description")
    private String description;



    
 
    public String getIdpermission() {
        return idpermission;
    }

    public void setIdpermission(String idpermission) {
        this.idpermission = idpermission;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
