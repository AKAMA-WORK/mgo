package com.mgo.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.panache.common.Parameters;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;


@Entity
@NamedQueries({
        @NamedQuery(name = "Departure.lockDeparture", query = "update Departure p SET locked_by_id_person = :lockedByIdPerson, lock_until = :lockUntil, version = :version WHERE id_departure=:departureId AND version = :version - 1"),
        @NamedQuery(name = "Departure.releaseDeparture", query = "update Departure p SET locked_by_id_person = null, lock_until = null, locked_by_phone=null  WHERE locked_by_id_person = :lockedByIdPerson AND id_departure = :departureId"),
})


@Table(name = "departure")
public class Departure extends PanacheEntityBase implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id_departure")
    private String departureId;

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

    @Column(name = "date_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTime;

    @Column(name = "end_date_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDateTime;

    @Column(name = "car_registration_number")
    private String carRegistrationNumber;
    @JoinColumn(name = "id_vehicle", referencedColumnName = "id_vehicle")
    @ManyToOne
    private Vehicle vehicle;

    @Column(name = "price",precision = 2)
    private Double price;

    @JoinColumn(name = "id_employee_driver", referencedColumnName = "id_employee")
    @ManyToOne
    private Employee driver;
    @JoinColumn(name = "id_employee_driver2", referencedColumnName = "id_employee")
    @ManyToOne
    private Employee driver2;

    @JoinColumn(name = "id_category", referencedColumnName = "id_category")
    @ManyToOne
    private Category category;

    @JoinColumn(name = "to_id_city", referencedColumnName = "id_city")
    @ManyToOne
    private City to;

    @JoinColumn(name = "from_id_city", referencedColumnName = "id_city")
    @ManyToOne
    private City from;

    @JoinColumn(name = "id_organization", referencedColumnName = "id_organization")
    @ManyToOne
    private Organization organization;

    @Column(name = "status",length = 50)
    private String status;//ON_DATE | CANCELED | etc ...

    @Column(name = "lock_until")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lockUntil;

    @Column(name = "version", columnDefinition = "int default 0")
    private Integer version;

    @Column(name = "locked_by_phone",length = 50)
    private String lockedByPhone;

    @JoinColumn(name = "locked_by_id_person", referencedColumnName = "id_person")
    @ManyToOne
    private Person lockedByPerson;


    @OneToMany(mappedBy = "departure")
    @LazyCollection(LazyCollectionOption.FALSE)
    private Collection<DepartureVehicleSeat> seats;


/*    @OneToMany(mappedBy = "departure")
    @LazyCollection(LazyCollectionOption.FALSE)
    private Collection<BookingLine> bookingLines;
*/

    @JoinColumn(name = "id_vehicle_type", referencedColumnName = "id_vehicle_type")
    @ManyToOne
    private VehicleType vehicleType;

    @Column(name = "seat_lines")
    private Integer lines;
    @Column(name = "seat_columns")
    private Integer columns;

    @Column(name = "available_seats")
    private Integer availableSeats;


    @Column(name = "total_seats")
    private Integer totalSeats;


    public Departure() {
    }

    public Integer getLines() {
        return lines;
    }

    public void setLines(Integer lines) {
        this.lines = lines;
    }

    public Integer getColumns() {
        return columns;
    }

    public void setColumns(Integer columns) {
        this.columns = columns;
    }

    public Collection<DepartureVehicleSeat> getSeats() {
        return seats;
    }

    public void setSeats(Collection<DepartureVehicleSeat> seats) {
        this.seats = seats;
    }

    public String getDepartureId() {
        return departureId;
    }

    public void setDepartureId(String departureId) {
        this.departureId = departureId;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getLockedByPhone() {
        return lockedByPhone;
    }

    public void setLockedByPhone(String lockedByPhone) {
        this.lockedByPhone = lockedByPhone;
    }

    public Person getLockedByPerson() {
        return lockedByPerson;
    }

    public void setLockedByPerson(Person lockedByPerson) {
        this.lockedByPerson = lockedByPerson;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public Date getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(Date endDateTime) {
        this.endDateTime = endDateTime;
    }

    public String getCarRegistrationNumber() {
        return carRegistrationNumber;
    }

    public void setCarRegistrationNumber(String carRegistrationNumber) {
        this.carRegistrationNumber = carRegistrationNumber;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }



    public Employee getDriver() {
        return driver;
    }

    public void setDriver(Employee driver) {
        this.driver = driver;
    }

    public Employee getDriver2() {
        return driver2;
    }

    public void setDriver2(Employee driver2) {
        this.driver2 = driver2;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public City getTo() {
        return to;
    }

    public void setTo(City to) {
        this.to = to;
    }

    public City getFrom() {
        return from;
    }

    public void setFrom(City from) {
        this.from = from;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getLockUntil() {
        return lockUntil;
    }

    public void setLockUntil(Date lockUntil) {
        this.lockUntil = lockUntil;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }



    public Integer getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(Integer availableSeats) {
        this.availableSeats = availableSeats;
    }

    public Integer getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(Integer totalSeats) {
        this.totalSeats = totalSeats;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public static int lockDeparture  (String departureId, Date lockUntil, String lockedByIdPerson, Integer version ){
        return update("#Departure.lockDeparture", Parameters.with("lockUntil",lockUntil).and("lockedByIdPerson",lockedByIdPerson).and("version",version).and("departureId",departureId));
    }

    public static int releaseDeparture  (String departureId, String lockedByIdPerson ){
        return update("#Departure.releaseDeparture", Parameters.with("departureId",departureId).and("lockedByIdPerson",lockedByIdPerson));
    }



}
