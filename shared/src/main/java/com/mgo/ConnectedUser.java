package com.mgo;

import io.quarkus.qute.TemplateData;

@TemplateData
public class ConnectedUser {
    String groups;
    String username;
    String name;
    String cin;
    String contact;
    Integer idcompany;
    String company;

    Integer idcompanyagency;
    String companyagency;

    public ConnectedUser() {
    }

    public String getGroups() {
        return groups;
    }

    public void setGroups(String groups) {
        this.groups = groups;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public Integer getIdcompany() {
        return idcompany;
    }

    public void setIdcompany(Integer idcompany) {
        this.idcompany = idcompany;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Integer getIdcompanyagency() {
        return idcompanyagency;
    }

    public void setIdcompanyagency(Integer idcompanyagency) {
        this.idcompanyagency = idcompanyagency;
    }

    public String getCompanyagency() {
        return companyagency;
    }

    public void setCompanyagency(String companyagency) {
        this.companyagency = companyagency;
    }

    

}
