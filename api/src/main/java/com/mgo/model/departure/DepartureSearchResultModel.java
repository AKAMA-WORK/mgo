package com.mgo.model.departure;


import com.mgo.model.common.SearchResultModel;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(name = "DepartureSearchResult")
public class DepartureSearchResultModel extends SearchResultModel<DepartureModel> {
    @Schema(name = "aggregate", description = "The aggregation", ref = "DepartureSearchResultAggregate")
    DepartureSearchResultAggregateModel aggregate;
    public DepartureSearchResultAggregateModel getAggregate() {
        return aggregate;
    }

    public void setAggregate(DepartureSearchResultAggregateModel aggregate) {
        this.aggregate = aggregate;
    }
}
