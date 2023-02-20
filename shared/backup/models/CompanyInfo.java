package com.mgo.models;

import com.mgo.entities.Company;


public class CompanyInfo{
    private Integer idCompany;
    private String name;



    
    public CompanyInfo() {
    }

    public CompanyInfo(Company company) {
        this.idCompany = company.getIdcompany();
        this.name=company.getName();
    }

    public Integer getIdCompany() {
        return idCompany;
    }

    public void setIdCompany(Integer idCompany) {
        this.idCompany = idCompany;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    

    
}