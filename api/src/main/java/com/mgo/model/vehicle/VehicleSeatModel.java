package com.mgo.model.vehicle;

import com.mgo.entity.VehicleSeat;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(name = "VehicleSeat")
public class VehicleSeatModel {

    @Schema(name = "vehicleSeatId", description = "The id of vehicle seat")
     String vehicleSeatId;

    @Schema(description = "The x")
    Integer x;

    @Schema(description = "The y")
    Integer y;

    @Schema(description = "The seat number. If -1:  cannot be booked seat, if -2 : driver seat")
    Integer seatNumber;

    public VehicleSeatModel() {
    }

    public VehicleSeatModel(VehicleSeat seat) {
        this.vehicleSeatId = seat.getVehicleSeatId();
        this.x= seat.getX();
        this.y= seat.getY();
        this.seatNumber = seat.getSeatNumber();
    }

    public String getVehicleSeatId() {
        return vehicleSeatId;
    }

    public void setVehicleSeatId(String vehicleSeatId) {
        this.vehicleSeatId = vehicleSeatId;
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
}