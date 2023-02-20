package com.mgo.model.booking;

import com.mgo.model.departure.DepartureSearchSortModel;
import com.mgo.query.CommonFieldComparisonType;
import com.mgo.query.StringFieldComparisons;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder(toBuilder = true)
@Schema(name = "BookingSearchFilter")
public class BookingSearchFilterModel {

    @Schema(name = "clientIdPerson", description = "The person id")
    CommonFieldComparisonType<String> clientIdPerson;

    @Schema(name = "categoryId", description = "The category id")
    CommonFieldComparisonType<String> categoryId;

    @Schema(name = "organizationId", description = "The organization id")
    CommonFieldComparisonType<String> organizationId;

    @Schema(name = "from", description = "The city from")
    CommonFieldComparisonType<String> from;

    @Schema(name = "to", description = "The city to")
    CommonFieldComparisonType<String> to;

    @Schema(name = "periodOfDays", description = "The period of days", enumeration = {"morning","afternoon","evening"},type = SchemaType.ARRAY)
    CommonFieldComparisonType<String> periodOfDays;


    public static DepartureSearchSortModel fromString(String str){
        return  null;
    }

    public CommonFieldComparisonType<String> getPeriodOfDays() {
        return periodOfDays;
    }

    public void setPeriodOfDays(CommonFieldComparisonType<String> periodOfDays) {
        this.periodOfDays = periodOfDays;
    }

    public CommonFieldComparisonType<String> getClientIdPerson() {
        return clientIdPerson;
    }

    public void setClientIdPerson(CommonFieldComparisonType<String> clientIdPerson) {
        this.clientIdPerson = clientIdPerson;
    }

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
}
