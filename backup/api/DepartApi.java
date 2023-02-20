package com.mgo.api;

import com.mgo.ConnectedUser;
import com.mgo.entities.*;
import com.mgo.models.CategoryInfo;
import com.mgo.models.CompanyInfo;
import com.mgo.models.DepartureInfoAdapter;
import com.mgo.models.PlaceInfo;
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

@Path("/departures")
// @Authenticated
public class DepartApi {
    @Context
    SecurityContext securityContext;

    @Inject
    UserService userService;

    ConnectedUser connectedUser;

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat searchDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    String PERIOD_SEPARATOR = " ";

    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<DepartureInfoAdapter> findDepart(@FormParam("from") Integer villeDepart,
            @FormParam("to") Integer villeDestination,
            @FormParam("company") Integer company,
            @FormParam("date") String date) throws ParseException {

        Period period = this.parsePeriod(date);

        Collection<Departure> departures = Departure.find("company = " + company
                + " AND (dateheure BETWEEN  '" + searchDateFormat.format(period.getStart())
                + "' AND '" + searchDateFormat.format(period.getEnd()) + "') AND startin=" + villeDepart
                + " AND destination="
                + villeDestination).list();

        return departures.stream()
                .map(departure -> {

                    DepartureInfoAdapter adapter = new DepartureInfoAdapter();
                    adapter.setId(departure.getIddeparture());
                    adapter.setInfo(Departures.getDepartureInfo(departure));

                    return adapter;
                }).collect(Collectors.toList());
    }

    private Period parsePeriod(String date) throws ParseException {
        // yyyy-MM-dd
        Date _dateStart = date.contains(PERIOD_SEPARATOR) ? dateFormat.parse(date.split(PERIOD_SEPARATOR)[0].trim())
                : dateFormat.parse(date.trim());
        Calendar tmpCal = Calendar.getInstance();
        tmpCal.setTime(_dateStart);
        tmpCal.set(Calendar.HOUR_OF_DAY, 0);
        tmpCal.set(Calendar.MINUTE, 0);
        tmpCal.set(Calendar.SECOND, 0);
        _dateStart = tmpCal.getTime();
        Date _dateEnd = date.contains(PERIOD_SEPARATOR) ? dateFormat.parse(date.split(PERIOD_SEPARATOR)[1].trim())
                : dateFormat.parse(date.trim());
        tmpCal = Calendar.getInstance();
        tmpCal.setTime(_dateEnd);
        tmpCal.set(Calendar.HOUR_OF_DAY, 23);
        tmpCal.set(Calendar.MINUTE, 59);
        tmpCal.set(Calendar.SECOND, 59);
        _dateEnd = tmpCal.getTime();

        return new Period(_dateStart, _dateEnd);

    }

    @POST
    @Path("/companies")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CompanyInfo> companies(@FormParam("from") Integer villeDepart,
            @FormParam("to") Integer villeDestination,
            @FormParam("date") String date) throws ParseException {

        System.out.println("companies");
        Period period = this.parsePeriod(date);

        String departureWhere = "(dateheure BETWEEN  '" + searchDateFormat.format(period.getStart())
                + "' AND '" + searchDateFormat.format(period.getEnd()) + "') AND startin=" + villeDepart
                + " AND destination="
                + villeDestination;

        System.out.println("idcompany IN (select distinct company from departure where " + departureWhere + ")");
        return Company.find("idcompany IN (select distinct company from departure where " + departureWhere + ")")
                .stream().map(c -> {
                    return new CompanyInfo((Company) c);
                }).collect(Collectors.toList());

    }

    @POST
    @Path("/categories")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CategoryInfo> categories(@FormParam("from") Integer villeDepart,
            @FormParam("to") Integer villeDestination,
            @FormParam("company") Integer company,
            @FormParam("date") String date
            ) throws ParseException {

                Period period = this.parsePeriod(date);


                List<Departure> departures = Departure.find("company = " + company
                + " AND (dateheure BETWEEN  '" + searchDateFormat.format(period.getStart())
                + "' AND '" + searchDateFormat.format(period.getEnd()) + "') AND startin=" + villeDepart + " AND destination="
                + villeDestination).list();


           List<CategoryInfo> result = new ArrayList<>();

           for(int i=0;i<departures.size();i++){
            Departure departure = departures.get(i);
            
            boolean found =  result.stream().anyMatch(category->{
                return category.getIdCategory()==departure.getCategory().getIdcategory() && category.getPrice()==departure.getPrice();

            });

            if(!found){
                result.add(new CategoryInfo(departure.getCategory(), departure.getPrice()));
            }

           }      

           return result;
           

    }

    /*
     * @GET
     * 
     * @Path("/depart/place")
     * 
     * @Produces(MediaType.APPLICATION_JSON)
     * public List<PlaceInfo> getPlaceInfoDeparture(@QueryParam("depart") Integer
     * depart) {
     * Departure departure = Departure.findById(depart);
     * Collection<Vehiclemappingplace> vehiclemappingplaces = Vehiclemappingplace
     * .find("vehicle=:vehicle", Parameters.with("vehicle",
     * departure.getVehicle())).list();
     * 
     * Collection<Booking> bookings = Booking.find("departure=:departure",
     * Parameters.with("departure", departure))
     * .list();
     * 
     * List<Integer> placeOccuped = new ArrayList<>();
     * bookings.forEach(booking -> {
     * booking.getBookingplaceCollection()
     * .forEach(bookingplace -> placeOccuped.add(bookingplace.getPlace()));
     * });
     * 
     * List<PlaceInfo> placeInfos = new ArrayList<>();
     * vehiclemappingplaces.forEach(vehiclemappingplace -> {
     * PlaceInfo placeInfo = new PlaceInfo();
     * placeInfo.setX(vehiclemappingplace.getX());
     * placeInfo.setY(vehiclemappingplace.getY());
     * placeInfo.setPlace(vehiclemappingplace.getPlacenumber());
     * placeInfo.setOccuped(placeOccuped.contains(vehiclemappingplace.getPlacenumber
     * ()));
     * placeInfo.setLine(departure.getVehicle().getVehicleplace().getNbligne());
     * placeInfo.setColumn(departure.getVehicle().getVehicleplace().getNbcolumn());
     * placeInfos.add(placeInfo);
     * });
     * return placeInfos;
     * }
     * 
     * @GET
     * 
     * @Path("/client/find")
     * 
     * @Produces(MediaType.APPLICATION_JSON)
     * public String searchClientByNumber(@QueryParam("phone") String phone) {
     * Long phoneToInt = Long.valueOf(phone.replaceAll("[^0-9.]", ""));
     * if (phoneToInt.toString().startsWith("261")) {
     * phoneToInt = Long.valueOf(phoneToInt.toString().replaceFirst("261", ""));
     * }
     * Client client = Client.find("phone = " + phoneToInt).firstResult();
     * 
     * if (client != null) {
     * return new JsonObject().put("found", true)
     * .put("civilite", client.getCivility())
     * .put("nom", client.getFname()).encode();
     * }
     * return new JsonObject().put("found", false).encode();
     * }
     */
}