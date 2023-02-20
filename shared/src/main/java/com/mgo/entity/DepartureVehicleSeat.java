
package com.mgo.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.panache.common.Parameters;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@NamedQueries({
        @NamedQuery(name = "DepartureVehicleSeat.getBySeatNumber", query = "from DepartureVehicleSeat where seatNumber =:seatNumber and id_departure=:departureId"),
        @NamedQuery(name = "DepartureVehicleSeat.lockSeat", query = "update DepartureVehicleSeat p SET locked_by_id_person = :lockedByIdPerson, lock_until = :lockUntil, version = :version WHERE departureVehicleSeatId = :departureVehicleSeatId AND version = :version - 1"),
        @NamedQuery(name = "DepartureVehicleSeat.releaseSeat", query = "update DepartureVehicleSeat p SET locked_by_id_person = null, lock_until = null, locked_by_phone=null  WHERE locked_by_id_person = :lockedByIdPerson AND id_departure_vehicle_seat = :departureVehicleSeatId"),

})


@Table(name = "departure_vehicle_seat")
public class DepartureVehicleSeat extends PanacheEntityBase implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id_departure_vehicle_seat")
    private String departureVehicleSeatId;

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

    @Column(name = "x")
    private Integer x;

    @Column(name = "y")
    private Integer y;

    @Column(name = "seat_number")
    private Integer seatNumber;

    @Column(name = "status",length = 50)
    private String status;// AVAILABLE | UNAVAILABLE | LOCKED

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

    @JoinColumn(name = "id_departure", referencedColumnName = "id_departure")
    @ManyToOne
    private Departure departure;

    public DepartureVehicleSeat() {
    }

    public String getDepartureVehicleSeatId() {
        return departureVehicleSeatId;
    }

    public void setDepartureVehicleSeatId(String departureVehicleSeatId) {
        this.departureVehicleSeatId = departureVehicleSeatId;
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

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Integer getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(Integer seatNumber) {
        this.seatNumber = seatNumber;
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

    public Departure getDeparture() {
        return departure;
    }

    public void setDeparture(Departure departure) {
        this.departure = departure;
    }


    public static DepartureVehicleSeat findBySeatNumber( String departureId, Integer seatNumber){
        return find("#DepartureVehicleSeat.getBySeatNumber", Parameters.with("departureId",departureId).and("seatNumber",seatNumber)).firstResult();
    }

    public static int lockSeat (String departureVehicleSeatId,  Date lockUntil, String lockedByIdPerson, Integer version ){
            return update("#DepartureVehicleSeat.lockSeat", Parameters.with("lockUntil",lockUntil).and("lockedByIdPerson",lockedByIdPerson).and("version",version).and("departureVehicleSeatId",departureVehicleSeatId));
    }

    public static int releaseSeat (String departureVehicleSeatId,  String lockedByIdPerson ){
        return update("#DepartureVehicleSeat.releaseSeat", Parameters.with("lockedByIdPerson",lockedByIdPerson).and("departureVehicleSeatId",departureVehicleSeatId));
    }
}
