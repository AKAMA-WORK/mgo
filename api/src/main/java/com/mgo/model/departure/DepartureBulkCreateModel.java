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
@Schema(name = "DepartureBulkCreate")
public class DepartureBulkCreateModel {

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

    @Schema(name = "date", description = "The departure date (2022-11-15) or period (2022-11-15 2022-11-20)", example = "2022-11-15")
    String date;

    @Schema(name = "time", description = "The departure times(08:00) or start and end time (08:00 12:00)", example = "08:00", type = SchemaType.ARRAY)
    List<String> time;

    @Schema(name = "seats", description = "The vehicles", ref = "DepartureBulkCreateVehicle",type = SchemaType.ARRAY)
    List<DepartureBulkCreateVehicleModel> vehicles;



    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<String> getTime() {
        return time;
    }

    public void setTime(List<String> time) {
        this.time = time;
    }

    public List<DepartureBulkCreateVehicleModel> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<DepartureBulkCreateVehicleModel> vehicles) {
        this.vehicles = vehicles;
    }
}