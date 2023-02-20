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
@Table(name = "companyagency")
public class Companyagency extends PanacheEntityBase implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcompanyagency")
    private Integer idcompanyagency;
    
    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @JoinColumn(name = "idcity", referencedColumnName = "idcity")
    @ManyToOne
    private City city;

    @Column(name = "phone")
    private String phone;

    @JoinColumn(name = "idcompany", referencedColumnName = "idcompany")
    @ManyToOne
    private Company company;



    public Integer getIdcompanyagency() {
        return idcompanyagency;
    }


    public void setIdcompanyagency(Integer idcompanyagency) {
        this.idcompanyagency = idcompanyagency;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getAddress() {
        return address;
    }


    public void setAddress(String address) {
        this.address = address;
    }


    public City getCity() {
        return city;
    }


    public void setCity(City city) {
        this.city = city;
    }


    public String getPhone() {
        return phone;
    }


    public void setPhone(String phone) {
        this.phone = phone;
    }


    public Company getCompany() {
        return company;
    }


    public void setCompany(Company company) {
        this.company = company;
    }


    
    
}
