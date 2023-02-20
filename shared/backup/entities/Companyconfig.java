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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author faraniarijaona
 */
@Entity
@Table(name = "companyconfig")
public class Companyconfig extends PanacheEntityBase implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idcompanyconfig")
    private Integer idcompanyconfig;
    @Column(name = "authorizedluggage")
    private Long authorizedluggage;
    @Column(name = "priceextraluggage")
    private Long priceextraluggage;
    @Column(name = "bookingcancelpenality")
    private Integer bookingcancelpenality;
    @Column(name = "transfercommission")
    private Integer transfercommission;
    @Column(name = "tiketlogourl")
    private String tiketlogourl;

    @JoinColumn(name = "company", referencedColumnName = "idcompany")
    @ManyToOne
    private Company company;

    public Companyconfig() {
    }

    public Companyconfig(Integer idcompanyconfig) {
        this.idcompanyconfig = idcompanyconfig;
    }

    public Integer getIdcompanyconfig() {
        return idcompanyconfig;
    }

    public void setIdcompanyconfig(Integer idcompanyconfig) {
        this.idcompanyconfig = idcompanyconfig;
    }

    public Long getAuthorizedluggage() {
        return authorizedluggage;
    }

    public void setAuthorizedluggage(Long authorizedluggage) {
        this.authorizedluggage = authorizedluggage;
    }

    public Long getPriceextraluggage() {
        return priceextraluggage;
    }

    public void setPriceextraluggage(Long priceextraluggage) {
        this.priceextraluggage = priceextraluggage;
    }

    public Integer getBookingcancelpenality() {
        return bookingcancelpenality;
    }

    public void setBookingcancelpenality(Integer bookingcancelpenality) {
        this.bookingcancelpenality = bookingcancelpenality;
    }

    public Integer getTransfercommission() {
        return transfercommission;
    }

    public void setTransfercommission(Integer transfercommission) {
        this.transfercommission = transfercommission;
    }

    public Company getCompany() {
        return company;
    }

    

    public String getTiketlogourl() {
        return tiketlogourl;
    }

    public void setTiketlogourl(String tiketlogourl) {
        this.tiketlogourl = tiketlogourl;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcompanyconfig != null ? idcompanyconfig.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Companyconfig)) {
            return false;
        }
        Companyconfig other = (Companyconfig) object;
        if ((this.idcompanyconfig == null && other.idcompanyconfig != null) || (this.idcompanyconfig != null && !this.idcompanyconfig.equals(other.idcompanyconfig))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "evoyage.Companyconfig[ idcompanyconfig=" + idcompanyconfig + " ]";
    }
    
}
