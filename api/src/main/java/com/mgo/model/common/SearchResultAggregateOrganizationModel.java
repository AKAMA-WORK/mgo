package com.mgo.model.common;


import com.mgo.entity.Organization;
import com.mgo.model.organization.OrganizationModel;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(name = "SearchResultAggregateOrganization")
public class SearchResultAggregateOrganizationModel extends OrganizationModel {
    @Schema(name = "totalCount", description = "The total count")
    long totalCount;

    public SearchResultAggregateOrganizationModel(long totalCount,
                                                  Organization entity) {
        super(entity);

        this.totalCount= totalCount;
    }


    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }
}