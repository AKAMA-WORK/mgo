package com.mgo.model.departure;


import org.eclipse.microprofile.openapi.annotations.media.Schema;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter(onMethod = {})
@Data
@Builder(toBuilder = true)
@Schema(name = "DepartureSearchSort")
public class DepartureSearchSortModel {

    @Schema(name = "price", description = "The sort by price", enumeration = {"ASC","DESC"})
    String price;

    @Schema(name = "date", description = "The sort by date", enumeration = {"ASC","DESC"})
    String date;


   public static DepartureSearchSortModel fromString(String str){

       return  null;
   }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
