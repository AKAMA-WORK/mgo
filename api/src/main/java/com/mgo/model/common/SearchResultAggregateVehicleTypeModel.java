package com.mgo.model.common;


import com.mgo.entity.VehicleType;
import com.mgo.model.vehicle.VehicleTypeModel;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(name = "SearchResultAggregateVehicleType")
public class SearchResultAggregateVehicleTypeModel extends VehicleTypeModel {
    @Schema(name = "totalCount", description = "The total count")
    long totalCount;

    public SearchResultAggregateVehicleTypeModel() {
    }

    public SearchResultAggregateVehicleTypeModel(long totalCount, VehicleType type) {
        super(type);
        this.totalCount = totalCount;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }
}