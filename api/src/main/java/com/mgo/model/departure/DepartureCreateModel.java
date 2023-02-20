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
@Schema(name = "DepartureCreate")
public class DepartureCreateModel {

    @Schema(name = "from", description = "The from city id")
    String from;

    @Schema(name = "to", description = "The to city id")
    String to;

    @Schema(name = "price", description = "The price")
    Double price;

    @Schema(name = "categoryId", description = "The category id")
    String categoryId;

    @Schema(name = "organizationId", description = "The organization id")
    String organizationId;

    @Schema(name = "date", description = "The departure date", example = "2022-11-15")
    String date;

    @Schema(name = "time", description = "The departure time", example = "08:00")
    String time;


    @Schema(name = "endDate", description = "The departure end date", example = "2022-11-15")
    String endDate;

    @Schema(name = "endTime", description = "The departure end time", example = "12:00")
    String endTime;

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

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}