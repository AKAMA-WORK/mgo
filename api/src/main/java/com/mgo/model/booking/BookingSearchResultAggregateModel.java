package com.mgo.model.booking;


import com.mgo.model.common.SearchResultAggregateCityModel;
import com.mgo.model.common.SearchResultAggregateOrganizationModel;
import com.mgo.model.common.SearchResultAggregateVehicleTypeModel;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.util.List;

@Schema(name = "BookingSearchResultAggregate")
public class BookingSearchResultAggregateModel {

    @Schema(name = "vehicleTypes", description = "The vehicle types aggregate", ref = "SearchResultAggregateVehicleType", type = SchemaType.ARRAY)
    List<SearchResultAggregateVehicleTypeModel> vehicleTypes;

    @Schema(name = "organizations", description = "The organization aggregate", ref = "SearchResultAggregateOrganization", type = SchemaType.ARRAY)
    List<SearchResultAggregateOrganizationModel> organizations;

    @Schema(name = "from", description = "The from city aggregate", ref = "SearchResultAggregateCity", type = SchemaType.ARRAY)
    List<SearchResultAggregateCityModel> from;

    @Schema(name = "to", description = "The to city aggregate", ref = "SearchResultAggregateCity", type = SchemaType.ARRAY)
    List<SearchResultAggregateCityModel> to;


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

    public List<SearchResultAggregateCityModel> getFrom() {
        return from;
    }

    public void setFrom(List<SearchResultAggregateCityModel> from) {
        this.from = from;
    }

    public List<SearchResultAggregateCityModel> getTo() {
        return to;
    }

    public void setTo(List<SearchResultAggregateCityModel> to) {
        this.to = to;
    }
}
