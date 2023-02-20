package com.mgo.model.departure;

import com.mgo.query.CommonFieldComparisonType;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder(toBuilder = true)
@Schema(name = "DepartureSearchFilter")
public class DepartureSearchFilterModel {
  /*  //?include=comments.author,ratings

    String include;
    //?fields[articles]=title,body&fields[people]=name
        String fields;

    //?sort=-created,title
    String sort;
    //page[offset]&page[limit]
    String page;

*/


    @Schema(name = "categoryId", description = "The category id")
    CommonFieldComparisonType<String> categoryId;

    @Schema(name = "vehicleTypeId", description = "The vehicle type")
    CommonFieldComparisonType<String> vehicleTypeId;

    @Schema(name = "date", description = "The date")
    CommonFieldComparisonType<String> date;

    @Schema(name = "organizationId", description = "The organization id")
    CommonFieldComparisonType<String> organizationId;

    @Schema(name = "from", description = "The city from")
    CommonFieldComparisonType<String> from;

    @Schema(name = "to", description = "The city to")
    CommonFieldComparisonType<String> to;


    @Schema(name = "availableSeats", description = "The available seats")
    CommonFieldComparisonType<Long> availableSeats;


    //vehiculeTypes
    @Schema(name = "periodOfDays", description = "The period of days", enumeration = {"morning","afternoon","evening"},type = SchemaType.ARRAY)
    CommonFieldComparisonType<String> periodOfDays;

    public CommonFieldComparisonType<String> getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(CommonFieldComparisonType<String> categoryId) {
        this.categoryId = categoryId;
    }

    public CommonFieldComparisonType<String> getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(CommonFieldComparisonType<String> organizationId) {
        this.organizationId = organizationId;
    }

    public CommonFieldComparisonType<String> getFrom() {
        return from;
    }

    public void setFrom(CommonFieldComparisonType<String> from) {
        this.from = from;
    }

    public CommonFieldComparisonType<String> getTo() {
        return to;
    }

    public void setTo(CommonFieldComparisonType<String> to) {
        this.to = to;
    }

    public static DepartureSearchSortModel fromString(String str){
        return  null;
    }


    public CommonFieldComparisonType<String> getDate() {
        return date;
    }

    public void setDate(CommonFieldComparisonType<String> date) {
        this.date = date;
    }


    public CommonFieldComparisonType<String> getVehicleTypeId() {
        return vehicleTypeId;
    }

    public void setVehicleTypeId(CommonFieldComparisonType<String> vehicleTypeId) {
        this.vehicleTypeId = vehicleTypeId;
    }

    public CommonFieldComparisonType<String> getPeriodOfDays() {
        return periodOfDays;
    }

    public void setPeriodOfDays(CommonFieldComparisonType<String> periodOfDays) {
        this.periodOfDays = periodOfDays;
    }

    public CommonFieldComparisonType<Long> getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(CommonFieldComparisonType<Long> availableSeats) {
        this.availableSeats = availableSeats;
    }

}
