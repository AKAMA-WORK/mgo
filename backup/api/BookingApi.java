package com.mgo.api;

import com.mgo.ConnectedUser;
import com.mgo.entities.*;
import com.mgo.models.BookingAdapter;
import com.mgo.services.UserService;
import io.quarkus.panache.common.Parameters;
import io.quarkus.security.Authenticated;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import java.awt.print.Book;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Path("/api")
@Authenticated
public class BookingApi {
    @Context
    SecurityContext securityContext;

    @Inject
    UserService userService;

    // SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /*@GET
    @Path("/booking")
    @Produces(MediaType.APPLICATION_JSON)
    public List<BookingAdapter> findActiveBooking(@QueryParam("msisdn") String msisdn) {
        ConnectedUser connectedUser = this.userService.getConnectedUser(this.securityContext);

        Date today = new Date();
        Calendar tmpCal = Calendar.getInstance();
        tmpCal.setTime(today);
        tmpCal.set(Calendar.HOUR_OF_DAY, 0);
        tmpCal.set(Calendar.MINUTE, 0);
        tmpCal.set(Calendar.SECOND, 0);
        today = tmpCal.getTime();

        List<Client> list = Client.find("phone = " + msisdn).list();

        if (list.size() == 0)
            return Collections.EMPTY_LIST;

        Client client = list.get(0);

        Collection<Departure> departures = Departure.find("company = " + connectedUser.getIdcompany() + " AND dateheure >  '" + dateFormat.format(today)+"'").list();

        if(departures.isEmpty()) {
            return Collections.EMPTY_LIST;
        }

        String idDepartures = departures.stream().map(Departure::getIddeparture).distinct().map(Object::toString).collect(Collectors.joining(","));

        List<Booking> bookings = Booking.find("client = :client  AND departure in ("+idDepartures+")",
                Parameters.with("client", client)
        ).list();

        return bookings.stream().map(booking -> {
            BookingAdapter bookingAdapter = new BookingAdapter();

            bookingAdapter.setIdbooking(booking.getIdbooking());
            bookingAdapter.setCategory(booking.getDeparture().getCategory().getName());
            if (booking.getDate() != null)
                bookingAdapter.setDate(dateFormat.format(booking.getDate()));
            if (booking.getDatepointage() != null)
                bookingAdapter.setDatepointage(dateFormat.format(booking.getDatepointage()));
            bookingAdapter.setAmount(booking.getAmount());
            bookingAdapter.setPaymentid(booking.getPaymentid());
            if (booking.getPaymentmethod() != null)
                bookingAdapter.setPaymentmethod(booking.getPaymentmethod().getName());
            if (booking.getConfirmdate() != null)
                bookingAdapter.setConfirmdate(dateFormat.format(booking.getConfirmdate()));
            if (booking.getCanceldate() != null)
                bookingAdapter.setCanceldate(dateFormat.format(booking.getCanceldate()));
            bookingAdapter.setDescription(booking.getDescription());
            bookingAdapter.setAuthorizedluggage(booking.getAuthorizedluggage());
            if (booking.getBookingluggageCollection() != null && booking.getBookingluggageCollection().size() > 0)
                bookingAdapter.setBookingluggageCollection(booking.getBookingluggageCollection().stream().map(Bookingluggage::getDescription).collect(Collectors.joining(" / ")));
            if (booking.getBookingstatus() != null)
                bookingAdapter.setBookingstatus(booking.getBookingstatus().getName());
            bookingAdapter.setClient(booking.getClient().getFname() + " " + booking.getClient().getLname());
            bookingAdapter.setMsisdn(booking.getClient().getPhone());
            if (booking.getTransferedfrom() != null)
                bookingAdapter.setTransferedfrom(booking.getTransferedfrom().getName());
            bookingAdapter.setDeparture(booking.getDeparture().getStartin().getName());
            bookingAdapter.setDestination(booking.getDeparture().getDestination().getName());
            bookingAdapter.setDepartureDate(dateFormat.format(booking.getDeparture().getDateheure()));
            if (booking.getBookingplaceCollection() != null && booking.getBookingplaceCollection().size() > 0)
                bookingAdapter.setBookingplaceCollection(booking.getBookingplaceCollection().stream().map(Bookingplace::getPlace)
                        .map(Object::toString).collect(Collectors.joining(" / ")));
            if (booking.getBookingextraluggageCollection() != null && booking.getBookingextraluggageCollection().size() > 0)
                bookingAdapter.setBookingextraluggageCollection(booking.getBookingextraluggageCollection().stream().map(Bookingextraluggage::getWeight).reduce(0L, Long::sum).toString());
            bookingAdapter.setBoarding(booking.getBoarding());
            if (booking.getBoardingDate() != null)
                bookingAdapter.setBoardingDate(dateFormat.format(booking.getBoardingDate()));

            return bookingAdapter;
        }).collect(Collectors.toList());
    }
*/
}