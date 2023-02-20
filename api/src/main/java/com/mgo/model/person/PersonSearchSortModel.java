package com.mgo.model.person;


import lombok.*;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@NoArgsConstructor
@AllArgsConstructor
@Getter(onMethod = {})
@Data
@Builder(toBuilder = true)
@Schema(name = "PersonSearchSort")
public class PersonSearchSortModel {

    @Schema(name = "civility", description = "The sort by civility", enumeration = {"ASC","DESC"})
    String civility;

    @Schema(name = "firstName", description = "The sort by first name", enumeration = {"ASC","DESC"})
    String firstName;

    @Schema(name = "lastName", description = "The sort by last name", enumeration = {"ASC","DESC"})
    String lastName;


   public static PersonSearchSortModel fromString(String str){

       return  null;
   }

    public String getCivility() {
        return civility;
    }

    public void setCivility(String civility) {
        this.civility = civility;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
