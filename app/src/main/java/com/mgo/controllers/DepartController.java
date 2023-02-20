package com.mgo.controllers;

import com.mgo.ConnectedUser;
import com.mgo.entity.Category;
import com.mgo.entity.City;
import com.mgo.entity.Vehicle;
import com.mgo.services.DepartureService;
import com.mgo.services.SearchDepartureResult;
import com.mgo.services.UserService;
import io.quarkus.panache.common.Sort;
import io.quarkus.qute.Location;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import io.quarkus.security.Authenticated;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Path("/depart")
@Authenticated
public class DepartController {
        @Context
        SecurityContext securityContext;

        @Inject
        UserService userService;

        @Inject
        DepartureService departureService;

        @Location("depart/ajouter.qute.html")
        Template ajouter;

        @Location("depart/rechercher.qute.html")
        Template rechercher;

        @Location("depart/details.qute.html")
        Template details;

        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        SimpleDateFormat searchDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        @GET
        @Path("/ajouter.html")
        @RolesAllowed({ "responsible" })
        @Produces(MediaType.TEXT_HTML)
        public TemplateInstance ajouter(String bootTrapDialogType, String message) {
                Boolean hasMessage = message.length() > 0;

                ConnectedUser connectedUser = this.userService.getConnectedUser(this.securityContext);
                List<City> cities = City.listAll(Sort.ascending("name"));
                List<Category> categories = Category.listAll();
                List<Vehicle> vehicles = Vehicle.listAll();
                /*List<VehicleAdapter> vehicleAdapters = vehicles.stream().map(vehicle -> new VehicleAdapter(
                                vehicle.getIdvehicle(),
                                vehicle.getVehicletype().getName().concat("(")
                                                .concat(vehicle.getVehicleplace().getNbtotalplace().toString())
                                                .concat(" places").concat(")")))
                                .collect(Collectors.toList());*/
                return ajouter.data("title", "Ajouter départ")
                                .data("connectedUser", connectedUser)
                                .data("cities", cities)
                                .data("categories", categories)
                                .data("hasMessage", hasMessage)
                                .data("bootTrapDialogType", bootTrapDialogType)
                                .data("message", message)
                                .data("vehicles",Collections.emptyList() /* vehicleAdapters*/);
        }

        @POST
        @Path("/ajouter.html")
        @RolesAllowed({ "responsible" })
        @Produces(MediaType.TEXT_HTML)

        @Transactional
        public TemplateInstance ajouterSave(@FormParam("ville_depart") int villeDepart,
                        @FormParam("ville_destination") int villeDestination,
                        @FormParam("frais") int frais,
                        @FormParam("category") int category,
                        @FormParam("date_depart") String dateDepart,
                        @FormParam("heure_depart") String[] heureDepart,
                        @FormParam("vehicle") String[] vehicle) throws ParseException {

                int counter = 0;
                ConnectedUser connectedUser = this.userService.getConnectedUser(this.securityContext);

                Date _dateStart = dateDepart.contains("-") ? dateFormat.parse(dateDepart.split("-")[0].trim())
                                : dateFormat.parse(dateDepart.trim());
                Date _dateEnd = dateDepart.contains("-") ? dateFormat.parse(dateDepart.split("-")[1].trim())
                                : dateFormat.parse(dateDepart.trim());

                Calendar start = Calendar.getInstance();
                start.setTime(_dateStart);
                Calendar end = Calendar.getInstance();
                end.setTime(_dateEnd);
                end.add(Calendar.DATE, 1);

                for (Date date = start.getTime(); start.before(end); start.add(Calendar.DATE,
                                1), date = start.getTime()) {
                        for (String s : heureDepart) {
                                Calendar tempStart = Calendar.getInstance();
                                tempStart.setTime(date);
                                tempStart.set(Calendar.HOUR_OF_DAY, Integer.parseInt(s.split(":")[0]));
                                tempStart.set(Calendar.MINUTE, Integer.parseInt(s.split(":")[1]));

                               /* for (String value : vehicle) {
                                        if ((new Date().getTime() < tempStart.getTime().getTime())
                                                        && (villeDepart != villeDestination)) {
                                                int idVehicle = Integer.parseInt(value);
                                                Departure departure = new Departure();
                                                departure.setStartin(City.findById(villeDepart));
                                                departure.setDestination(City.findById(villeDestination));
                                                departure.setDateheure(tempStart.getTime());
                                                departure.setVehicle(Vehicle.findById(idVehicle));
                                                departure.setPrice(frais);
                                                departure.setCategory(Category.findById(category));
                                                departure.setDeparturestatus(Departurestatus.findById(1));
                                                departure.setCompany(Company.findById(connectedUser.getIdcompany()));
                                                departure.setCompanyagency(connectedUser.getIdcompanyagency() != null
                                                                ? Companyagency.findById(
                                                                                connectedUser.getIdcompanyagency())
                                                                : null);
                                                try {
                                                        departure.persistAndFlush();
                                                        counter++;
                                                } catch (Exception e) {
                                                        e.printStackTrace();
                                                }
                                        }
                                }*/
                        }
                }

                return ajouter("alert-success",
                                counter + (counter == 1 ? " départ a été ajouté" : " départs ont été ajouté"));
        }

        @GET
        @Path("/rechercher.html")
        @Produces(MediaType.TEXT_HTML)
        public TemplateInstance rechercher() {
                ConnectedUser connectedUser = this.userService.getConnectedUser(this.securityContext);

                /*Companyagency companyagency = connectedUser.getIdcompanyagency() != null
                                ? (Companyagency.findById(connectedUser.getIdcompanyagency()))

                                : null;*/

                List<City> cities = City.listAll(Sort.ascending("name"));
                return rechercher.data("title", "Rechercher départ")
                                .data("connectedUser", connectedUser)
                                .data("cities", cities)
                                .data("isResult", false)
                                .data("cityDepartId",
                                               /* companyagency != null && companyagency.getCity() != null
                                                                ? companyagency.getCity().getIdcity()
                                                                :*/ "")
                                .data("cityDestinationId", "")
                                .data("cityDepart", "")
                                .data("cityDestination", "")
                                .data("dateStart", "")
                                .data("dateEnd", "")
                                .data("departures", Collections.EMPTY_LIST);
        }

        public TemplateInstance rechercher(Boolean isResult,
                        City cityDepart,
                        City cityDestination,
                        String dateStart,
                        String dateEnd,
                        Collection data) {
                ConnectedUser connectedUser = this.userService.getConnectedUser(this.securityContext);
                List<City> cities = City.listAll(Sort.ascending("name"));
                return rechercher.data("title", "Rechercher départ")
                                .data("connectedUser", connectedUser)
                                .data("cities", cities)
                                .data("isResult", isResult)
                                .data("cityDepartId", /*cityDepart != null ? cityDepart.getIdcity() :*/ "")
                                .data("cityDestinationId", /*cityDestination != null ? cityDestination.getIdcity() :*/ "")
                                .data("cityDepart", cityDepart != null ? cityDepart.getName().toUpperCase() : "")
                                .data("cityDestination",
                                                cityDestination != null ? cityDestination.getName().toUpperCase() : "")
                                .data("dateStart", dateStart)
                                .data("dateEnd", dateEnd)
                                .data("departures", data)
                                .data("showDeparturesCities", false);
        }

        @POST
        @Path("/rechercher.html")
        @Produces(MediaType.TEXT_HTML)
        public TemplateInstance rechercherDepart(@FormParam("ville_depart") int villeDepart,
                        @FormParam("ville_destination") int villeDestination,
                        @FormParam("date_depart") String dateDepart) throws ParseException {
                ConnectedUser connectedUser = this.userService.getConnectedUser(this.securityContext);

                SearchDepartureResult result = this.departureService.findDepartures(connectedUser, villeDepart,
                                villeDestination, dateDepart, false);

                return rechercher(true,
                                ((City) City.findById(villeDepart)),
                                ((City) City.findById(villeDestination)),
                                new SimpleDateFormat("dd/MM/yyyy").format(result.getDateStart()),
                                new SimpleDateFormat("dd/MM/yyyy").format(result.getDateEnd())
                                                .equalsIgnoreCase(new SimpleDateFormat("dd/MM/yyyy")
                                                                .format(result.getDateStart()))
                                                                                ? null
                                                                                : new SimpleDateFormat("dd/MM/yyyy")
                                                                                                .format(result.getDateEnd()),
                               /* result.getDepartures()*/Collections.emptyList());
        }

        @GET
        @Path("/detail.html")
        @Produces(MediaType.TEXT_HTML)
        public TemplateInstance detail(@QueryParam("id") int id) {
                ConnectedUser connectedUser = this.userService.getConnectedUser(this.securityContext);

             /*   Departure departure = Departure.findById(id);
                DepartureAdapter adapter = new DepartureAdapter();
                adapter.setId(departure.getIddeparture());
                adapter.setDateDepart(new SimpleDateFormat("dd/MM/yyyy").format(departure.getDateheure()));
                adapter.setHeureDepart(new SimpleDateFormat("HH:mm").format(departure.getDateheure()));
                adapter.setCategory(departure.getCategory().getName());
                adapter.setStatus(departure.getDeparturestatus().getStatus());
                adapter.setVehicle(departure.getVehicle().getVehicletype().getName().concat("(")
                                .concat(departure.getVehicle().getVehicleplace().getNbtotalplace().toString())
                                .concat(" places")
                                .concat(")"));
                Collection<Booking> bookings = departure.getBookingCollection();
                Collection<Bookingplace> collection = new ArrayList();
                if (bookings.size() > 0) {
                        bookings.forEach(booking -> collection.addAll(booking.getBookingplaceCollection()));
                }
                adapter.setPlaceLibre(
                                String.valueOf(departure.getVehicle().getVehicleplace().getNbtotalplace()
                                                - collection.size()));
                adapter.setPlaceTotale(String.valueOf(departure.getVehicle().getVehicleplace().getNbtotalplace()));
                adapter.setVilleDepart(departure.getStartin().getName());
                adapter.setVilleDestination(departure.getDestination().getName());

                List<Vehicle> vehicles = Vehicle.listAll();
                List<VehicleAdapter> vehicleAdapters = vehicles
                                .stream()
                                .filter(vehicle -> vehicle.getIdvehicle().equals(departure.getVehicle().getIdvehicle())
                                                ||
                                                vehicle.getVehicleplace().getNbtotalplace() > departure
                                                                .getVehicle().getVehicleplace().getNbtotalplace())
                                .map(vehicle -> new VehicleAdapter(vehicle.getIdvehicle(),
                                                vehicle.getVehicletype().getName().concat("(")
                                                                .concat(vehicle.getVehicleplace().getNbtotalplace()
                                                                                .toString())
                                                                .concat(" places")
                                                                .concat(")")))
                                .collect(Collectors.toList());
*/
                return details.data("title", "Détails Départ #" +"1" /*adapter.getId()*/)
                                .data("connectedUser", connectedUser)
                                .data("vehicles", /*vehicleAdapters*/Collections.emptyList())
                                .data("departure",/* adapter*/null);
        }

        @POST
        @Path("/detail.html")
        @RolesAllowed({ "responsible" })
        @Produces(MediaType.TEXT_HTML)
        @Transactional
        public TemplateInstance detail(@FormParam("departure") int departure, @FormParam("vehicle") int vehicle) {
               // Departure departure1 = Departure.findById(departure);
               // departure1.setVehicle(Vehicle.findById(vehicle));
                return detail(departure);
        }

        @GET
        @Path("/annuler.html")
        @RolesAllowed({ "responsible" })
        @Produces(MediaType.TEXT_HTML)
        @Transactional
        public TemplateInstance annuler(@QueryParam("id") int departure) {
               /* Departure departure1 = Departure.findById(departure);
                Collection<Booking> bookings = departure1.getBookingCollection();
                Collection<Bookingplace> collection = new ArrayList();
                if (bookings.size() > 0) {
                        bookings.forEach(booking -> collection.addAll(booking.getBookingplaceCollection()));
                }

                if (collection.size() == 0) {
                        departure1.setDeparturestatus(Departurestatus.findById(2));
                }*/
                return detail(departure);
        }
}
