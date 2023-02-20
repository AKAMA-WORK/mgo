package com.mgo.model.person;


import com.mgo.model.common.SearchResultModel;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(name = "PersonSearchResult")
public class PersonSearchResultModel extends SearchResultModel<PersonModel> {
}
