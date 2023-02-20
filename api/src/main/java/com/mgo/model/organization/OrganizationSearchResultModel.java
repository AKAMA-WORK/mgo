package com.mgo.model.organization;


import com.mgo.model.common.SearchResultModel;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(name = "OrganizationSearchResult")
public class OrganizationSearchResultModel extends SearchResultModel<OrganizationModel> {
}
