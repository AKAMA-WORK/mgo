package com.mgo.model.person;

import com.mgo.query.CommonFieldComparisonType;
import com.mgo.query.StringFieldComparisons;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.media.Schema;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder(toBuilder = true)
@Schema(name = "PersonSearchFilter")
public class PersonSearchFilterModel {


    @Schema(name = "userId", description = "The user id")
    CommonFieldComparisonType<String> userId;

    @Schema(name = "personId", description = "The person from")
    CommonFieldComparisonType<String> personId;


    public static PersonSearchFilterModel fromString(String str){

        return  null;
    }

    public CommonFieldComparisonType<String> getUserId() {
        return userId;
    }

    public void setUserId(CommonFieldComparisonType<String> userId) {
        this.userId = userId;
    }

    public CommonFieldComparisonType<String> getPersonId() {
        return personId;
    }

    public void setPersonId(CommonFieldComparisonType<String> personId) {
        this.personId = personId;
    }
}
