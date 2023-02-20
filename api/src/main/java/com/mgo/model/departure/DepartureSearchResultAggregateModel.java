package com.mgo.model.departure;


import com.mgo.model.common.SearchResultAggregateOrganizationModel;
import com.mgo.model.common.SearchResultAggregatePeriodOfDayModel;
import com.mgo.model.common.SearchResultAggregateVehicleTypeModel;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.util.List;

@Schema(name = "DepartureSearchResultAggregate")
public class DepartureSearchResultAggregateModel {
    @Schema(name = "periodOfDays", description = "The period of day aggregate", ref = "SearchResultAggregatePeriodOfDay", type = SchemaType.ARRAY)
    List<SearchResultAggregatePeriodOfDayModel> periodOfDays;

    @Schema(name = "vehicleTypes", description = "The vehicle types aggregate", ref = "SearchResultAggregateVehicleType", type = SchemaType.ARRAY)
    List<SearchResultAggregateVehicleTypeModel> vehicleTypes;

    @Schema(name = "organizations", description = "The organizations aggregate", ref = "SearchResultAggregateOrganization", type = SchemaType.ARRAY)
    List<SearchResultAggregateOrganizationModel> organizations;

    public List<SearchResultAggregatePeriodOfDayModel> getPeriodOfDays() {
        return periodOfDays;
    }

    public void setPeriodOfDays(List<SearchResultAggregatePeriodOfDayModel> periodOfDays) {
        this.periodOfDays = periodOfDays;
    }

    public List<SearchResultAggregateVehicleTypeModel> getVehicleTypes() {
        return vehicleTypes;
    }

    public void setVehicleTypes(List<SearchResultAggregateVehicleTypeModel> vehicleTypes) {
        this.vehicleTypes = vehicleTypes;
    }

    public List<SearchResultAggregateOrganizationModel> getOrganizations() {
        return organizations;
    }

    public void setOrganizations(List<SearchResultAggregateOrganizationModel> organizations) {
        this.organizations = organizations;
    }
}
