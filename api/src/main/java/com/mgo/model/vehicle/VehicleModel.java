package com.mgo.model.vehicle;


import com.mgo.entity.Vehicle;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.util.List;
import java.util.stream.Collectors;

@Schema(name = "Vehicle")
public class VehicleModel {

    @Schema(name = "vehicleId", description = "The id of vehicle")
    String vehicleId;

    @Schema(description = "The registration number")
     String carRegistrationNumber;

    @Schema(description = "The vehicle type", ref = "VehicleType")
    VehicleTypeModel vehicleType;

    @Schema(description = "The lines count")
    Integer lines;

    @Schema(description = "The columns count")
    Integer columns;

    @Schema(description = "The seats", ref = "VehicleSeat", type = SchemaType.ARRAY)
    List<VehicleSeatModel> seats;

    public VehicleModel() {
    }

    public VehicleModel(Vehicle vehicle) {
        this.vehicleId = vehicle.getVehicleId();
        this.carRegistrationNumber = vehicle.getCarRegistrationNumber();
        this.vehicleType = vehicle.getVehicleType()!=null ? new VehicleTypeModel(vehicle.getVehicleType()) : null;
        this.lines = vehicle.getLines();
        this.columns = vehicle.getColumns();
        this.seats = vehicle.getSeats().stream().map(VehicleSeatModel::new).collect(Collectors.toList());
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getCarRegistrationNumber() {
        return carRegistrationNumber;
    }

    public void setCarRegistrationNumber(String carRegistrationNumber) {
        this.carRegistrationNumber = carRegistrationNumber;
    }

    public VehicleTypeModel getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleTypeModel vehicleType) {
        this.vehicleType = vehicleType;
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