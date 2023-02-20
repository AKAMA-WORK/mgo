package com.mgo.model.departure;

import com.mgo.entity.DepartureVehicleSeat;
import com.mgo.model.person.PersonModel;
import com.mgo.util.DateTimeFormat;
import com.mgo.util.DateTimeUtils;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.util.Date;

@Schema(name = "DepartureVehicleSeat")
public class DepartureVehicleSeatModel {

    @Schema(name = "departureVehicleSeatId", description = "The id")
    String departureVehicleSeatId;

    @Schema(description = "The x")
    Integer x;

    @Schema(description = "The y")
    Integer y;

    @Schema(description = "The seat number. If -1:  cannot be booked seat, if -2 : driver seat")
    Integer seatNumber;

    @Schema(name = "status", description = "The status",enumeration = {
            DepartureVehicleSeatStatus.AVAILABLE,DepartureVehicleSeatStatus.BOOKED,DepartureVehicleSeatStatus.LOCKED
    })
    String status;

    @Schema(name = "lockUntil", description = "Lock seat until")
    String lockUntil;

    @Schema(name = "unlockIn", description = "Unlock in (seconds)")
    long unlockIn;


    @Schema(name = "lockedBy", description = "Lock seat by", ref = "Person")
    PersonModel lockedBy;

    @Schema(name = "lockedByPhone", description = "Lock seat by phone number")
    String lockedByPhone;


    public DepartureVehicleSeatModel() {
    }

    public DepartureVehicleSeatModel(DepartureVehicleSeat seat, Date now) {
        this.departureVehicleSeatId = seat.getDepartureVehicleSeatId();
        this.x= seat.getX();
        this.y= seat.getY();
        this.seatNumber = seat.getSeatNumber();
        this.status= seat.getStatus();
        this.lockUntil = DateTimeFormat.formatDatetime(seat.getLockUntil());
        this.lockedBy  = seat.getLockedByPerson() !=null ? new PersonModel(seat.getLockedByPerson()) : null;
        this.lockedByPhone = seat.getLockedByPhone();

        this.computeLock(seat,now);

    }

    private void computeLock(DepartureVehicleSeat seat,Date now){
        if((seat.getLockedByPerson()!=null || seat.getLockedByPhone()!=null) && (
         DepartureVehicleSeatStatus.AVAILABLE.equals(seat.getStatus()) ||
                 DepartureVehicleSeatStatus.LOCKED.equals(seat.getStatus())
        )
        ){
              this.unlockIn = DateTimeUtils.secondsBetween(new Date(),seat.getLockUntil());
              this.status =this.unlockIn<=0 ?DepartureVehicleSeatStatus.AVAILABLE: DepartureVehicleSeatStatus.LOCKED;
        }
    }

    public String getDepartureVehicleSeatId() {
        return departureVehicleSeatId;
    }

    public void setDepartureVehicleSeatId(String departureVehicleSeatId) {
        this.departureVehicleSeatId = departureVehicleSeatId;
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

    public String getLockUntil() {
        return lockUntil;
    }

    public void setLockUntil(String lockUntil) {
        this.lockUntil = lockUntil;
    }

    public PersonModel getLockedBy() {
        return lockedBy;
    }

    public void setLockedBy(PersonModel lockedBy) {
        this.lockedBy = lockedBy;
    }

    public String getLockedByPhone() {
        return lockedByPhone;
    }

    public void setLockedByPhone(String lockedByPhone) {
        this.lockedByPhone = lockedByPhone;
    }

    public long getUnlockIn() {
        return unlockIn;
    }

    public void setUnlockIn(long unlockIn) {
        this.unlockIn = unlockIn;
    }
}