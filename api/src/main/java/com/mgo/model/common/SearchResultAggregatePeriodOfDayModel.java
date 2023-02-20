package com.mgo.model.common;

import lombok.*;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.util.Objects;

//@NoArgsConstructor
//@AllArgsConstructor
@Getter(onMethod = {})
@Data
//@Builder(toBuilder = true)
@Schema(name = "SearchResultAggregatePeriodOfDay")
public class SearchResultAggregatePeriodOfDayModel extends PeriodOfDayModel {
    @Schema(name = "totalCount", description = "The total count")
    long totalCount;

    public SearchResultAggregatePeriodOfDayModel() {
    }

    public SearchResultAggregatePeriodOfDayModel(String id, String label, String startHour, String endHour, long totalCount) {
        super(id, label, startHour, endHour);
        this.totalCount = totalCount;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SearchResultAggregatePeriodOfDayModel that = (SearchResultAggregatePeriodOfDayModel) o;
        return totalCount == that.totalCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), totalCount);
    }
}
