package com.mgo.controllers;

import com.mgo.ConnectedUser;
import com.mgo.entity.City;
import com.mgo.entity.Organization;
import com.mgo.services.DepartureService;
import com.mgo.services.SearchDepartureResult;
import com.mgo.services.UserService;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import io.quarkus.security.Authenticated;
import io.quarkus.panache.common.Sort;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

@Path("/acceuil.html")
@Authenticated
public class AcceuilController {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

        @Context
        SecurityContext securityContext;

        @Inject
        UserService userService;

        @Inject
        DepartureService departureService;

        @Inject
        Template dashboard;

        @GET
        @Produces(MediaType.TEXT_HTML)
        public TemplateInstance home() throws ParseException {
                ConnectedUser connectedUser = this.userService.getConnectedUser(this.securityContext);
                long clientsCount = 0; //Client.count();
                long bookingCount = 0;//Booking.count();
                long departureCount = 0; //Departure.count("company=" + connectedUser.getIdcompany());
                long bordingBookingCount =0; // Booking.count("boarding = 1");

                Organization companyagency = /*connectedUser.getIdcompanyagency() != null
                                ? (Companyagency.findById(connectedUser.getIdcompanyagency()))

                                :*/ null;

                SearchDepartureResult result =/* this.departureService.findDepartures(connectedUser,
                                companyagency != null && companyagency.getCity() != null
                                                ? companyagency.getCity().getIdcity()
                                                : null,
                                null, dateFormat.format(new Date()), true)*/null;

                List<City> cities = City.listAll(Sort.ascending("name"));

                return dashboard.data("title", "Tableau de Bord")
                                .data("clientsCount", clientsCount)
                                .data("bookingCount", bookingCount)
                                .data("departureCount", departureCount)
                                .data("bordingBookingCount", bordingBookingCount)
                                .data("connectedUser", connectedUser)
                                .data("departures", Collections.emptyList() /* result.getDepartures()*/)
                                .data("cities", cities)
                                .data("cityDepartId",
                                               /* companyagency != null && companyagency.getCity() != null
                                                                ? companyagency.getCity().getIdcity()
                                                                :*/ "")
                                .data("dateStart", "")
                                .data("dateEnd", "")
                                .data("cityDestinationId", "")
                                .data("showDeparturesCities", true);
        }
}
