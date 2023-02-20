package com.mgo.model.booking;


import com.mgo.model.departure.DepartureSearchSortModel;
import lombok.*;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter(onMethod = {})
@Data
@Builder(toBuilder = true)
@Schema(name = "BookingSearchOptions")
public class BookingSearchOptionsModel {

    @Schema(name = "includes", description = "Includes data",type = SchemaType.ARRAY, enumeration = {"aggregate","driver","from","to","organization","seats","vehicle","vehicleSeats"})
    List<String> includes;



    public static DepartureSearchSortModel fromString(String str){

        return  null;
    }

    public List<String> getIncludes() {
        return includes;
    }

    public void setIncludes(List<String> includes) {
        this.includes = includes;
    }
}
