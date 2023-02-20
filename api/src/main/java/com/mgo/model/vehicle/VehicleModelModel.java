package com.mgo.model.vehicle;


import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.util.List;

@Schema(name = "VehicleModel")
public class VehicleModelModel {

    @Schema(name = "vehicleModelId", description = "The id of model")
    String vehicleModelId;

    @Schema(description = "The name of model")
     String name;


    @Schema(description = "The lines count")
    Integer lines;

    @Schema(description = "The columns count")
    Integer columns;

    @Schema(description = "The seats", ref = "VehicleModelSeat", type = SchemaType.ARRAY)
    List<VehicleSeatModel> seats;

    public VehicleModelModel() {
    }

    public String getVehicleModelId() {
        return vehicleModelId;
    }

    public void setVehicleModelId(String vehicleModelId) {
        this.vehicleModelId = vehicleModelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<VehicleSeatModel> getSeats() {
        return seats;
    }

    public void setSeats(List<VehicleSeatModel> seats) {
        this.seats = seats;
    }
}