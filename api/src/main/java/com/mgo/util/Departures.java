package com.mgo.util;

import com.mgo.entity.Departure;
import com.mgo.model.departure.DepartureVehicleSeatStatus;


public class Departures {
    
    public static String getDepartureInfo(Departure departure) {

         long availablePlaces = departure.getSeats().stream().filter(seat -> seat.getStatus().equals(DepartureVehicleSeatStatus.AVAILABLE)).count();
         long places = departure.getSeats().size();
        
        return DateTimeFormat.DATETIME_LONG_FORMAT.format(departure.getDateTime()) +
                " - " + departure.getCategory().getName() + " - " + departure.getPrice() +
                " - " + (places - availablePlaces) + " Places Libres";
    }
}
