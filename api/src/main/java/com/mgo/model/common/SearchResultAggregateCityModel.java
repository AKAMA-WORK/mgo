package com.mgo.model.common;


import com.mgo.entity.City;
import com.mgo.model.city.CityModel;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(name = "SearchResultAggregateCity")
public class SearchResultAggregateCityModel extends CityModel {
    @Schema(name = "totalCount", description = "The total count")
    long totalCount;

    public SearchResultAggregateCityModel() {
    }

    public SearchResultAggregateCityModel(long totalCount, City type) {
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