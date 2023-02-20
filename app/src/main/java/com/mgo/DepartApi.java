package com.mgo;


import com.mgo.services.UserService;
import com.mgo.util.Departures;

import io.quarkus.panache.common.Parameters;
import io.quarkus.security.Authenticated;
import io.vertx.core.json.JsonObject;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Path("/api")
@Authenticated
public class DepartApi {
    @Context
    SecurityContext securityContext;

    @Inject
    UserService userService;

    ConnectedUser connectedUser;

    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    SimpleDateFormat searchDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @POST
    @Path("/depart")
    @Produces(MediaType.APPLICATION_JSON)
    public List findDepart(@FormParam("villeDepart") String villeDepart,
            @FormParam("villeDestination") String villeDestination,
            @FormParam("date") String date) throws ParseException {

        this.connectedUser = this.userService.getConnectedUser(this.securityContext);

        Date _dateStart = date.contains("-") ? dateFormat.parse(date.split("-")[0].trim())
                : dateFormat.parse(date.trim());
        Calendar tmpCal = Calendar.getInstance();
        tmpCal.setTime(_dateStart);
        tmpCal.set(Calendar.HOUR_OF_DAY, 0);
        tmpCal.set(Calendar.MINUTE, 0);
        tmpCal.set(Calendar.SECOND, 0);
        _dateStart = tmpCal.getTime();
        Date _dateEnd = date.contains("-") ? dateFormat.parse(date.split("-")[1].trim())
                : dateFormat.parse(date.trim());
        tmpCal = Calendar.getInstance();
        tmpCal.setTime(_dateEnd);
        tmpCal.set(Calendar.HOUR_OF_DAY, 23);
        tmpCal.set(Calendar.MINUTE, 59);
        tmpCal.set(Calendar.SECOND, 59);
        _dateEnd = tmpCal.getTime();


        return Collections.emptyList();

       /* Collection<Departure> departures = Departure.find("company = " + this.connectedUser.getIdcompany()
                + " AND (dateheure BETWEEN  '" + searchDateFormat.format(_dateStart)
                + "' AND '" + searchDateFormat.format(_dateEnd) + "') AND startin=" + villeDepart + " AND destination="
                + villeDestination).list();


        return departures.stream()
                .map(departure -> {

                    DepartureInfoAdapter adapter = new DepartureInfoAdapter();
                    adapter.setId(departure.getIddeparture());
                    adapter.setInfo(Departures.getDepartureInfo(departure));

                    return  adapter;
                }).collect(Collectors.toList());*/
    }
/*
    @GET
    @Path("/depart/place")
    @Produces(MediaType.APPLICATION_JSON)
    public List<PlaceInfo> getPlaceInfoDeparture(@QueryParam("depart") Integer depart) {
        Departure departure = Departure.findById(depart);
        Collection<Vehiclemappingplace> vehiclemappingplaces = Vehiclemappingplace
                .find("vehicle=:vehicle", Parameters.with("vehicle", departure.getVehicle())).list();

        Collection<Booking> bookings = Booking.find("departure=:departure", Parameters.with("departure", departure))
                .list();

        List<Integer> placeOccuped = new ArrayList<>();
        bookings.forEach(booking -> {
            booking.getBookingplaceCollection()
                    .forEach(bookingplace -> placeOccuped.add(bookingplace.getPlace()));
        });

        List<PlaceInfo> placeInfos = new ArrayList<>();
        vehiclemappingplaces.forEach(vehiclemappingplace -> {
            PlaceInfo placeInfo = new PlaceInfo();
            placeInfo.setX(vehiclemappingplace.getX());
            placeInfo.setY(vehiclemappingplace.getY());
            placeInfo.setPlace(vehiclemappingplace.getPlacenumber());
            placeInfo.setOccuped(placeOccuped.contains(vehiclemappingplace.getPlacenumber()));
            placeInfo.setLine(departure.getVehicle().getVehicleplace().getNbligne());
            placeInfo.setColumn(departure.getVehicle().getVehicleplace().getNbcolumn());
            placeInfos.add(placeInfo);
        });
        return placeInfos;
    }*/

    @GET
    @Path("/client/find")
    @Produces(MediaType.APPLICATION_JSON)
    public String searchClientByNumber(@QueryParam("phone") String phone) {
       /* Long phoneToInt = Long.valueOf(phone.replaceAll("[^0-9.]", ""));
        if (phoneToInt.toString().startsWith("261")) {
            phoneToInt = Long.valueOf(phoneToInt.toString().replaceFirst("261", ""));
        }
        Client client = Client.find("phone = " + phoneToInt).firstResult();

        if (client != null) {
            return new JsonObject().put("found", true)
                    .put("civilite", client.getCivility())
                    .put("nom", client.getFname()).encode();
        }*/
        return new JsonObject().put("found", false).encode();
    }
}