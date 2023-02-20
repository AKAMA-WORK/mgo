package com.mgo.model.departure;


import lombok.*;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter(onMethod = {})
@Data
@Builder(toBuilder = true)
@Schema(name = "DepartureBulkCreateVehicle")
public class DepartureBulkCreateVehicleModel {


    /**
     * We can choose :
     *  - A vehicle
     *  - A vehicle model and eventually vehicle type
     *  - lines, columns,seats and eventually vehicle type
     *
     */
    @Schema(name = "vehicleId", description = "The id of vehicle")
    String vehicleId;

    @Schema(name = "vehicleModelId", description = "The id of model")
    String vehicleModelId;

    @Schema(name = "vehicleTypeId", description = "The id of vehicle type")
    String vehicleTypeId;

    @Schema(description = "The lines count")
    Integer lines;

    @Schema(description = "The columns count")
    Integer columns;

    @Schema(name = "seats", description = "The seats", ref = "DepartureCreateVehicleSeat",type = SchemaType.ARRAY)
    List<DepartureCreateVehicleSeatModel> seats;

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVehicleModelId() {
        return vehicleModelId;
    }

    public void setVehicleModelId(String vehicleModelId) {
        this.vehicleModelId = vehicleModelId;
    }

    public String getVehicleTypeId() {
        return vehicleTypeId;
    }

    public void setVehicleTypeId(String vehicleTypeId) {
        this.vehicleTypeId = vehicleTypeId;
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

    public List<DepartureCreateVehicleSeatModel> getSeats() {
        return seats;
    }

    public void setSeats(List<DepartureCreateVehicleSeatModel> seats) {
        this.seats = seats;
    }
}