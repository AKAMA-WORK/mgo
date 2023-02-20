package com.mgo.model.departure;

import java.util.List;


import com.mgo.entity.Vehicle;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(name = "DepartureVehicle")
public class DepartureVehicleModel {

    @Schema(description = "The name of vehicle")
     String name;

    @Schema(description = "The lines count")
    Integer lines;

    @Schema(description = "The columns count")
    Integer columns;

    @Schema(description = "The number of available seats", example = "18")
     Integer availableSeats;

    @Schema(description = "The number of seats", example = "18")
     Integer totalSeats;

    @Schema(description = "The seats", ref = "DepartureVehicleSeat", type = SchemaType.ARRAY)
    List<DepartureVehicleSeatModel> seats;

    public DepartureVehicleModel() {
    }

    public DepartureVehicleModel(
            Vehicle vehicle,
            Integer availablePlaces,
            List<DepartureVehicleSeatModel> places) {
        this.seats = places;
        this.availableSeats = availablePlaces;
       // this.setName(vehicle.getVehicletype().getName());
       // this.setColumns(vehicle.getVehicleplace().getNbcolumn());
       // this.setLines(vehicle.getVehicleplace().getNbligne());
       // this.setTotalSeats(vehicle.getVehicleplace().getNbtotalplace());

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

    public List<DepartureVehicleSeatModel> getSeats() {
        return seats;
    }

    public void setSeats(List<DepartureVehicleSeatModel> seats) {
        this.seats = seats;
    }
}