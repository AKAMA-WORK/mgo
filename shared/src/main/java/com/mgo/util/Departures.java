package com.mgo.util;

import com.mgo.entity.Departure;

public class Departures {
    
    public static String getDepartureInfo(Departure departure) {

       /* Collection<Booking> bookings = departure.getBookingCollection();
        Collection<Bookingplace> collection = new ArrayList<>();
        if (bookings.size() > 0) {
            bookings.forEach(booking -> collection.addAll(booking.getBookingplaceCollection()));
        }

        
        return new StringBuilder(new SimpleDateFormat("dd MMM yyyy à HH:mm").format(departure.getDateheure()))
        .append(" - ").append(departure.getCategory().getName()).append(" - ").append(departure.getPrice())
        .append(" - ").append(departure.getVehicle().getVehicleplace().getNbtotalplace() - collection.size()).append(" Places Libres").toString();
*/

        return "";
    }
}
