package com.mgo.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.enterprise.context.ApplicationScoped;

import com.mgo.ConnectedUser;
import com.mgo.entity.Departure;

@ApplicationScoped
public class DepartureService {
    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    SimpleDateFormat searchDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public SearchDepartureResult findDepartures(
            ConnectedUser connectedUser,
            Integer villeDepart,
            Integer villeDestination,
            String dateStartAndDateEnd,
            boolean ignoreDateEnd
            ) throws ParseException {

        Date dateStart = dateStartAndDateEnd.contains("-")
                ? dateFormat.parse(dateStartAndDateEnd.split("-")[0].trim())
                : dateFormat.parse(dateStartAndDateEnd.trim());
        Calendar tmpCal = Calendar.getInstance();
        tmpCal.setTime(dateStart);
        tmpCal.set(Calendar.HOUR_OF_DAY, 0);
        tmpCal.set(Calendar.MINUTE, 0);
        tmpCal.set(Calendar.SECOND, 0);
        dateStart = tmpCal.getTime();

        Date dateEnd = dateStartAndDateEnd.contains("-")
                ? dateFormat.parse(dateStartAndDateEnd.split("-")[1].trim())
                : dateFormat.parse(dateStartAndDateEnd.trim());
        tmpCal = Calendar.getInstance();
        tmpCal.setTime(dateEnd);
        tmpCal.set(Calendar.HOUR_OF_DAY, 23);
        tmpCal.set(Calendar.MINUTE, 59);
        tmpCal.set(Calendar.SECOND, 59);
        dateEnd = tmpCal.getTime();

        StringBuffer departuresQuery = new StringBuffer();
        departuresQuery.append("company = ").append(connectedUser.getIdcompany());

        if (dateStart != null && dateEnd != null && !ignoreDateEnd) {
            departuresQuery.append("AND (dateheure BETWEEN '")
                    .append(searchDateFormat.format(dateStart)).append("' AND '")
                    .append(searchDateFormat.format(dateEnd)).append("')");
        } else if (dateStart != null) {
            departuresQuery.append("AND (dateheure>='").append(searchDateFormat.format(dateStart))
                    .append("')");
        } else if (dateEnd != null) {
            departuresQuery.append("AND (dateheure<='").append(searchDateFormat.format(dateEnd))
                    .append("')");

        }

        if (villeDepart != null) {
            departuresQuery.append("AND startin=").append(villeDepart);
        }

        if (villeDestination != null) {
            departuresQuery.append("AND destination=").append(villeDestination);

        }

        Collection<Departure> departures = Departure.find(departuresQuery.toString()).list();

      /*  Collection<DepartureAdapter> departureAdapters = Collections.emptyList();  departures.stream().map(departure -> {
            DepartureAdapter adapter = new DepartureAdapter();
            adapter.setId(departure.getIddeparture());
            adapter.setDateDepart(new SimpleDateFormat("dd/MM/yyyy").format(departure.getDateheure()));
            adapter.setHeureDepart(new SimpleDateFormat("HH:mm").format(departure.getDateheure()));
            adapter.setCategory(departure.getCategory().getName());
            adapter.setStatus(departure.getDeparturestatus().getStatus());
            adapter.setVehicle(departure.getVehicle().getVehicletype().getName() + " "
                    + departure.getVehicle().getVehicleplace().getNbtotalplace() + " places");
            Collection<Booking> bookings = departure.getBookingCollection();
            Collection<Bookingplace> collection = new ArrayList();
            if (bookings.size() > 0) {
                bookings.forEach(booking -> collection.addAll(booking.getBookingplaceCollection()));
            }
            adapter.setPlaceLibre(
                    String.valueOf(departure.getVehicle().getVehicleplace().getNbtotalplace()
                            - collection.size()));
            adapter.setVilleDepart(departure.getStartin().getName());
            adapter.setVilleDestination(departure.getDestination().getName());
            return adapter;
        }).collect(Collectors.toList());*/

        return new SearchDepartureResult();

    }

}
