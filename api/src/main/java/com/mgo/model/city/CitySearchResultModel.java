package com.mgo.model.city;


import com.mgo.model.common.SearchResultModel;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(name = "CitySearchResult")
public class CitySearchResultModel extends SearchResultModel<CityModel> {
}
