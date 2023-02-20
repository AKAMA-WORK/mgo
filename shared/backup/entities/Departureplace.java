package com.mgo.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "departureplace")
public class Departureplace extends PanacheEntityBase implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iddepartureplace")
    private Integer iddepartureplace;

    @JoinColumn(name = "departure", referencedColumnName = "iddeparture")
    @ManyToOne
    private Departure departure;

    @Column(name = "status")
    private String status;// FREE ou BOOKED

    @Column(name = "lockuntil")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lockuntil;

    @Column(name = "place")
    private Integer place;

    @Column(name = "version")
    private Integer version;

    @Column(name = "ip")
    private String ip;

    @Column(name = "phone")
    private String phone;

    @JoinColumn(name = "user", referencedColumnName = "iduser")
    @ManyToOne
    private User user;

    public Departureplace() {
    }

    public Integer getIddepartureplace() {
        return iddepartureplace;
    }

    public void setIddepartureplace(Integer iddepartureplace) {
        this.iddepartureplace = iddepartureplace;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Departure getDeparture() {
        return departure;
    }

    public void setDeparture(Departure departure) {
        this.departure = departure;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getLockuntil() {
        return lockuntil;
    }

    public void setLockuntil(Date lockuntil) {
        this.lockuntil = lockuntil;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }


    public Integer getPlace() {
        return place;
    }

    public void setPlace(Integer place) {
        this.place = place;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
