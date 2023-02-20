package com.mgo.model.vehicle;

import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(name = "VehicleModelSeat")
public class VehicleModelSeatModel {

    @Schema(name = "vehicleModelSeatId", description = "The id of vehicle seat")
     String vehicleModelSeatId;

    @Schema(description = "The x")
    Integer x;

    @Schema(description = "The y")
    Integer y;

    @Schema(description = "The seat number. If -1:  cannot be booked seat, if -2 : driver seat")
    Integer seatNumber;

    public String getVehicleModelSeatId() {
        return vehicleModelSeatId;
    }

    public void setVehicleModelSeatId(String vehicleModelSeatId) {
        this.vehicleModelSeatId = vehicleModelSeatId;
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