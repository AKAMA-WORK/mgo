package com.mgo.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "vehicle")
public class Vehicle extends PanacheEntityBase implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id_vehicle")
    private String vehicleId;


    @Column(name = "creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date creationDate;


    @JoinColumn(name = "creation_id_person", referencedColumnName = "id_person")
    @ManyToOne
    private Person createdBy;


    @Column(name = "update_date")
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date updatedDate;

    @JoinColumn(name = "update_id_person", referencedColumnName = "id_person")
    @ManyToOne
    private Person updatedBy;

    @Column(name = "creation_client_id")
    private String createdClientId;

    @Column(name = "update_client_id")
    private String updateClientId;

    @JoinColumn(name = "id_vehicle_type", referencedColumnName = "id_vehicle_type")
    @ManyToOne
    private VehicleType vehicleType;

    @JoinColumn(name = "id_organization", referencedColumnName = "id_organization")
    @ManyToOne
    private Organization organization;

    @Column(name = "car_registration_number")
    private String carRegistrationNumber;

    @OneToMany(mappedBy = "vehicle", fetch = FetchType.EAGER)
    private Collection<VehicleSeat> seats;

    @Column(name = "seat_lines")
    private Integer lines;
    @Column(name = "seat_columns")
    private Integer columns;


    public Vehicle() {
    }

    public Integer getLines() {
        return lines;
    }

    public Integer getColumns() {
        return columns;
    }

    public void setColumns(Integer columns) {
        this.columns = columns;
    }

    public void setLines(Integer lines) {
        this.lines = lines;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Person getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Person createdBy) {
        this.createdBy = createdBy;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Person getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Person updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getCreatedClientId() {
        return createdClientId;
    }

    public void setCreatedClientId(String createdClientId) {
        this.createdClientId = createdClientId;
    }

    public String getUpdateClientId() {
        return updateClientId;
    }

    public void setUpdateClientId(String updateClientId) {
        this.updateClientId = updateClientId;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public String getCarRegistrationNumber() {
        return carRegistrationNumber;
    }

    public void setCarRegistrationNumber(String carRegistrationNumber) {
        this.carRegistrationNumber = carRegistrationNumber;
    }

    public Collection<VehicleSeat> getSeats() {
        return seats;
    }

    public void setSeats(Collection<VehicleSeat> seats) {
        this.seats = seats;
    }
}
