package com.mgo.model.booking;


import lombok.*;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@NoArgsConstructor
@AllArgsConstructor
@Getter(onMethod = {})
@Data
@Builder(toBuilder = true)
@Schema(name = "BookingSearchSort")
public class BookingSearchSortModel {

    @Schema(name = "price", description = "The sort by price", enumeration = {"ASC","DESC"})
    String price;

    @Schema(name = "date", description = "The sort by date", enumeration = {"ASC","DESC"})
    String date;


   public static BookingSearchSortModel fromString(String str){

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
