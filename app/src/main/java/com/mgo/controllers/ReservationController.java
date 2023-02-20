package com.mgo.controllers;

import com.mgo.ConnectedUser;
import com.mgo.entity.City;
import com.mgo.generator.TiketGenerator;
import com.mgo.services.UserService;
import com.mgo.util.Departures;
import com.mgo.util.Strings;

import io.quarkus.panache.common.Parameters;
import io.quarkus.panache.common.Sort;
import io.quarkus.qute.Location;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Path("/reservation")
public class ReservationController {
    SimpleDateFormat referenceDateFormat = new SimpleDateFormat("yyMMdd.HH:mm:ss");


    @Context
    SecurityContext securityContext;

    @Inject
    UserService userService;

    @Location("reservation/ajouter.qute.html")
    Template ajouter;

    @Location("reservation/rechercher.qute.html")
    Template rechercher;

    @Location("reservation/confirmation.qute.html")
    Template confirmation;

    @Location("reservation/bagage.qute.html")
    Template bagage;

    @GET
    @Path("/ajouter.html")
    @RolesAllowed({ "responsible" })
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance ajouterReservation(@QueryParam("departure_id") String idDepart, String bootTrapDialogType,
            String message) {
        Boolean hasMessage = message.length() > 0;
        ConnectedUser connectedUser = this.userService.getConnectedUser(this.securityContext);
        List<City> cities = City.listAll(Sort.ascending("name"));
       /* List<Paymentmethod> paymentmethods = Paymentmethod.listAll();

        Departure departure = idDepart != null ? Departure.find("iddeparture = " + idDepart).firstResult() : null;
        String departureInfo = departure != null ? Departures.getDepartureInfo(departure) : null;*/
        return ajouter.data("title", "Ajouter départ")
                .data("cities", cities)
                .data("paymentmethods",/* paymentmethods*/null)
                .data("hasMessage", hasMessage)
                .data("bootTrapDialogType", bootTrapDialogType)
                .data("message", message)
                .data("connectedUser", connectedUser)
                .data("departure",/* departure*/null)
                .data("departureInfo", /*departureInfo*/null);
    }

    @POST
    @Path("/ajouter.html")
    @RolesAllowed({ "responsible" })
    @Produces(MediaType.TEXT_HTML)
    @Transactional
    public TemplateInstance enregistrerReservation(@FormParam("depart") String idDepart,
            @FormParam("places") String places,
            @FormParam("phone") String phone,
            @FormParam("civilite") String civilite,
            @FormParam("nom") String nom,
            @FormParam("mode_paiement") String paimentMethod,
            @FormParam("reference") String reference,
            String bootTrapDialogType, String message) {

        if(Strings.isEmptyOrNull(reference) && !Strings.isEmptyOrNull(phone)){
            Date now = new Date();
             reference = phone+"."+referenceDateFormat.format(now)+"."+now.getTime();
        }          



       /* Departure departure = Departure.findById(Integer.parseInt(idDepart));
        Integer phoneToInt = Integer.valueOf(phone.replaceAll("[^0-9.]", ""));
        Client client = Client.find("phone = " + phoneToInt).firstResult();
        if (client == null) {
            client = new Client();
            client.persistAndFlush();
        }
        client.setPhone(phoneToInt);
        client.setCivility(civilite);
        client.setFname(nom.length() > 45 ? nom.substring(0, 44) : nom);

        List<Integer> listPlaces = Arrays.stream(places.split("-")).map(Integer::parseInt).collect(Collectors.toList());

        Booking booking = new Booking();
        booking.setDeparture(departure);
        booking.setClient(client);
        booking.setPaymentid(reference);
        booking.setPaymentmethod(Paymentmethod.findById(Integer.parseInt(paimentMethod)));
        booking.setAmount(listPlaces.size() * departure.getPrice());
        booking.setDate(new Date());
        // set booking confirmed / supposed payment not differed
        booking.setBookingstatus(Bookingstatus.findById(2));
        booking.setConfirmdate(new Date());
        booking.setBoarding(false);
        booking.persistAndFlush();

        listPlaces.forEach(integer -> {
            Bookingplace bookingplace = new Bookingplace();
            bookingplace.setBooking(booking);
            bookingplace.setPlace(integer);
            bookingplace.persistAndFlush();
        });*/
        return ajouterReservation(idDepart, "alert-success", "Résérvation prise en compte");
    }

    @GET
    @Path("/rechercher.html")
    @RolesAllowed({ "responsible" })
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance searchBooking(@QueryParam("msisdn") String msisdn) {
        String normalizedMsisdn = Strings.normalizeMsisdn(msisdn);

        ConnectedUser connectedUser = this.userService.getConnectedUser(this.securityContext);
       // List<BookingAdapter> bookingAdapters = this.findActiveBooking(normalizedMsisdn, connectedUser);
        return rechercher.data("bookingAdapters",/* bookingAdapters*/Collections.emptyList()).data("msisdn", normalizedMsisdn).data(
                "connectedUser",
                connectedUser);
    }

    @GET
    @Path("/confirmation.html")
    @RolesAllowed({ "responsible" })
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance details(@QueryParam("id") String idReservation) {
        ConnectedUser connectedUser = this.userService.getConnectedUser(this.securityContext);
       /* Booking booking = Booking.findById(Integer.parseInt(idReservation));

        BookingAdapter bookingAdapter = new BookingAdapter(booking);

        List<Paymentmethod> paymentmethods = Paymentmethod.listAll();*/

        return confirmation.data("connectedUser", connectedUser)
                .data("bookingAdapter", /*bookingAdapter*/null)
                .data("booking",/* booking*/null)
                .data("paymentmethods", /*paymentmethods*/Collections.emptyList());
    }

    @POST
    @Path("/confirmation.html")
    @RolesAllowed({ "responsible" })
    @Produces(MediaType.TEXT_HTML)
    @Transactional
    public TemplateInstance saveDetails(@FormParam("idbooking") String idReservation,
            @FormParam("montant") String montant,
            @FormParam("mode_paiement") String mode_paiement,
            @FormParam("reference") String reference,
            @FormParam("isenregistre") String isenregistre) {
        ConnectedUser connectedUser = this.userService.getConnectedUser(this.securityContext);
       /* Booking booking = Booking.findById(Integer.parseInt(idReservation));
        booking.setBoarding(isenregistre != "null");
        booking.setBoardingDate(new Date());
        booking.setPaymentid(reference);
        booking.setPaymentmethod(Paymentmethod.findById(Integer.parseInt(mode_paiement)));

        booking.persistAndFlush();

        BookingAdapter bookingAdapter = new BookingAdapter(booking);

        List<Paymentmethod> paymentmethods = Paymentmethod.listAll();
*/
        return confirmation.data("connectedUser", connectedUser)
                .data("bookingAdapter", /*bookingAdapter*/null)
                .data("booking", /*booking*/null)
                .data("paymentmethods", /*paymentmethods*/Collections.emptyList());
    }

    @GET
    @Path("/bagages.html")
    @RolesAllowed({ "responsible" })
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance bagage(@QueryParam("id") String idReservation, String bootTrapDialogType, String message) {
        Boolean hasMessage = message.length() > 0;

        ConnectedUser connectedUser = this.userService.getConnectedUser(this.securityContext);
       /* Booking booking = Booking.findById(Integer.parseInt(idReservation));
        BookingAdapter bookingAdapter = new BookingAdapter(booking);
        List<Paymentmethod> paymentmethods = Paymentmethod.listAll();
        Companyconfig companyconfig = Companyconfig.find("company = " + connectedUser.getIdcompany().toString())
                .firstResult();
        Bookingluggage bookingluggage = Bookingluggage.find("booking = " + idReservation).firstResult();
        Bookingextraluggage bookingextraluggage = Bookingextraluggage.find("booking = " + idReservation).firstResult();
*/
        return bagage.data("connectedUser", connectedUser)
               /* .data("bookingAdapter", bookingAdapter)
                .data("booking", booking)
                .data("bookingluggage", bookingluggage)
                .data("bookingextraluggage", bookingextraluggage)
                .data("companyconfig", companyconfig)
                .data("paymentmethods", paymentmethods)*/
                .data("hasMessage", hasMessage)
                .data("bootTrapDialogType", bootTrapDialogType);
    }

    @POST
    @Path("/bagages.html")
    @RolesAllowed({ "responsible" })
    @Produces(MediaType.TEXT_HTML)
    @Transactional
    public TemplateInstance saveBagage(
            @FormParam("idbooking") String idReservation,
            @FormParam("luggage-details") String details,
            @FormParam("weight") String weight,
            @FormParam("extra-weight") String extraWeight,
            @FormParam("extra-weight-amount") String extraWeightAmount,
            @FormParam("mode_paiement") String modePaiment,
            @FormParam("reference") String reference

    ) {

       /* Booking booking = Booking.findById(Integer.parseInt(idReservation));
        Bookingluggage bookingluggage = null;
        Bookingextraluggage bookingextraluggage = null;

        // Delete if all fields empty
        if (Strings.isEmptyOrNull(details) && Strings.isEmptyOrNull(weight)) {
            Bookingluggage.delete("booking = " + idReservation);
        } else {
            bookingluggage = Bookingluggage.find("booking = " + idReservation).firstResult();
            if (bookingluggage == null) {
                bookingluggage = new Bookingluggage();
                bookingluggage.setDate(new Date());
            }
            bookingluggage.setBooking(booking);
            bookingluggage.setDescription(details);
            bookingluggage.setWeight(Long.parseLong(weight));

            bookingluggage.persistAndFlush();
        }

        if (Strings.isEmptyOrNull(extraWeight)
                && Strings.isEmptyOrNull(extraWeightAmount)) {
            Bookingluggage.delete("booking = " + idReservation);
        } else {
            bookingextraluggage = Bookingextraluggage.find("booking = " + idReservation).firstResult();
            if (bookingextraluggage == null) {
                bookingextraluggage = new Bookingextraluggage();
            }
            bookingextraluggage.setAmount(Long.parseLong(extraWeightAmount));
            bookingextraluggage.setBooking(booking);
            bookingextraluggage.setPaymentid(reference);
            bookingextraluggage.setPaymentmethod(Strings.isEmptyOrNull(modePaiment)
                    ? Paymentmethod.findById(Integer.parseInt(modePaiment))
                    : null);
            bookingextraluggage.setWeight(Long.parseLong(extraWeight));
            bookingextraluggage.persistAndFlush();
        }
*/
        return this.bagage(idReservation, "alert-success", "Enregistré");
    }

    @GET
    @Path("/generate-ticket.png")
    // @RolesAllowed({ "responsible" })
    @Produces("image/png")
    public Response generateTicket(@QueryParam("id") String idReservation) {
        // BufferedImage image;
       /* try {

            Booking booking = Booking.find("id=" + idReservation).firstResult();
            Companyconfig companyconfig = Companyconfig.find("company = " + booking.getDeparture().getCompany().getIdcompany())
            .firstResult();

            if (booking != null) {
                return Response.ok(new TiketGenerator().generate(booking,companyconfig)).build();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }*/

        return Response.serverError().build();
    }

   /* private List<BookingAdapter> findActiveBooking(String msisdn, ConnectedUser connectedUser) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date today = new Date();
        Calendar tmpCal = Calendar.getInstance();
        tmpCal.setTime(today);
        tmpCal.setTime(today);
        tmpCal.set(Calendar.HOUR_OF_DAY, 0);
        tmpCal.set(Calendar.MINUTE, 0);
        tmpCal.set(Calendar.SECOND, 0);
        today = tmpCal.getTime();

        List<Client> list = Client.find("phone = " + msisdn).list();

        if (list.size() == 0)
            return Collections.EMPTY_LIST;

        Client client = list.get(0);

        Collection<Departure> departures = Departure.find(
                "company = " + connectedUser.getIdcompany() + " AND dateheure >  '" + dateFormat.format(today) + "'")
                .list();

        if (departures.isEmpty()) {
            return Collections.EMPTY_LIST;
        }

        String idDepartures = departures.stream().map(Departure::getIddeparture).distinct().map(Object::toString)
                .collect(Collectors.joining(","));

        List<Booking> bookings = Booking.find("client = :client  AND departure in (" + idDepartures + ")",
                Parameters.with("client", client)).list();

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
                bookingAdapter.setBookingluggageCollection(booking.getBookingluggageCollection().stream()
                        .map(Bookingluggage::getDescription).collect(Collectors.joining(" / ")));
            if (booking.getBookingstatus() != null)
                bookingAdapter.setBookingstatus(booking.getBookingstatus().getName());
            bookingAdapter.setClient((booking.getClient().getFname() != null ? booking.getClient().getFname() : "")
                    + " " + (booking.getClient().getLname() != null ? booking.getClient().getLname() : ""));
            bookingAdapter.setMsisdn(booking.getClient().getPhone());
            if (booking.getTransferedfrom() != null)
                bookingAdapter.setTransferedfrom(booking.getTransferedfrom().getName());
            bookingAdapter.setDeparture(booking.getDeparture().getStartin().getName());
            bookingAdapter.setDestination(booking.getDeparture().getDestination().getName());
            bookingAdapter.setDepartureDate(dateFormat.format(booking.getDeparture().getDateheure()));
            if (booking.getBookingplaceCollection() != null && booking.getBookingplaceCollection().size() > 0)
                bookingAdapter.setBookingplaceCollection(
                        booking.getBookingplaceCollection().stream().map(Bookingplace::getPlace)
                                .map(Object::toString).collect(Collectors.joining(" / ")));
            if (booking.getBookingextraluggageCollection() != null
                    && booking.getBookingextraluggageCollection().size() > 0)
                bookingAdapter.setBookingextraluggageCollection(booking.getBookingextraluggageCollection().stream()
                        .map(Bookingextraluggage::getWeight).reduce(0L, Long::sum).toString());
            bookingAdapter.setBoarding(booking.getBoarding());
            if (booking.getBoardingDate() != null)
                bookingAdapter.setBoardingDate(dateFormat.format(booking.getBoardingDate()));

            return bookingAdapter;
        }).collect(Collectors.toList());
    }*/
}
