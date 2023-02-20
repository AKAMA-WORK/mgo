package com.mgo.model.booking;


import com.mgo.model.common.SearchResultModel;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(name = "BookingSearchResult")
public class BookingSearchResultModel extends SearchResultModel<BookingModel> {
    @Schema(name = "aggregate", description = "The aggregation", ref = "BookingSearchResultAggregate")
    BookingSearchResultAggregateModel aggregate;

    public BookingSearchResultAggregateModel getAggregate() {
        return aggregate;
    }

    public void setAggregate(BookingSearchResultAggregateModel aggregate) {
        this.aggregate = aggregate;
    }
}
