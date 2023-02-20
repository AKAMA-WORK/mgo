package com.mgo.model.departure;


import lombok.*;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;


@NoArgsConstructor
@AllArgsConstructor
@Getter(onMethod = {})
@Data
@Builder(toBuilder = true)
@Schema(name = "DepartureCreateVehicleSeat")
public class DepartureCreateVehicleSeatModel {

    @Schema(description = "The x")
    Integer x;

    @Schema(description = "The y")
    Integer y;

    @Schema(description = "The seat number. If -1:  cannot be booked seat, if -2 : driver seat")
    Integer seatNumber;


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