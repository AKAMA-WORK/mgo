package com.mgo.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mgo.ConnectedUser;
import com.mgo.DepartApi;
import com.mgo.services.UserService;
import com.mgo.util.Departures;
import com.mgo.util.Strings;

import io.quarkus.qute.Location;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import java.io.ByteArrayOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.xhtmlrenderer.pdf.ITextRenderer;

@Path("/manifold")
public class ManifoldController {
        private static ObjectMapper mapper = new ObjectMapper();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat datetimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        @Context
        SecurityContext securityContext;

        @Inject
        UserService userService;

        @Inject
        DepartApi departApi;

        @Location("manifold/form.qute.html")
        Template form;

        @Location("manifold/generate-manifold.qute.html")
        Template export;

        @GET
        @Path("/generate-manifold.pdf")
        // @RolesAllowed({ "responsible" })
        @Produces("application/pdf")
        public Response generateManifold(@QueryParam("departure_id") String idDeparture) {
                // BufferedImage image;
               /* try {

                        Departure departure = Departure.find("iddeparture = " + idDeparture).firstResult();
                        List<DeparturemanifoldPlaceInfo> manifoldPlaceInfos = this.getManifoldPlaceInfos(departure);
                        Company company = departure.getCompany();

                        String html = export
                                        .data("company", company)
                                        .data("departure", departure)
                                        .data("departureDatetime", datetimeFormat.format(departure.getDateheure()))
                                        .data("manifoldPlaceInfos", manifoldPlaceInfos)
                                        .data("columns", departure.getVehicle().getVehicleplace().getNbcolumn())
                                        .data("lines", departure.getVehicle().getVehicleplace().getNbligne())
                                        .data("columnWidth",
                                                        100 / departure.getVehicle().getVehicleplace().getNbcolumn())
                                        .render();

                        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

                        ITextRenderer renderer = new ITextRenderer();
                        renderer.setDocumentFromString(html);
                        renderer.layout();
                        renderer.createPDF(outputStream);

                        outputStream.close();

                        return Response.ok(outputStream.toByteArray()).build();
                } catch (Exception e) {
                        e.printStackTrace();
                }*/

                return Response.serverError().build();
        }

        @GET
        @Path("/form.html")
        @RolesAllowed({ "responsible" })
        @Produces(MediaType.TEXT_HTML)
        public TemplateInstance manifoldForm(@QueryParam("departure_id") String idDepart, String bootTrapDialogType,
                        String message) {
               /* Boolean hasMessage = message.length() > 0;
                ConnectedUser connectedUser = this.userService.getConnectedUser(this.securityContext);
                List<Companyemployee> drivers = Companyemployee
                                .find("company=" + connectedUser.getIdcompany() + " AND role=6").list(); // 6
                                                                                                         // :
                                                                                                         // Driver


                List<Idtype> idtypes = Idtype.listAll();

                Departure departure = Departure.find("iddeparture = " + idDepart).firstResult();
                String departureInfo = Departures.getDepartureInfo(departure);

                JsonArray idtypesJson = new JsonArray();

                for (int i = 0; i < idtypes.size(); i++) {
                        Idtype type = idtypes.get(i);
                        idtypesJson.add(new JsonObject()
                                        .put("ididtype", type.getIdidtype())
                                        .put("idtypecol", type.getIdtypecol()));
                }

                List<DeparturemanifoldPlaceInfo> manifoldPlaceInfos = this.getManifoldPlaceInfos(departure);
                Company company = departure.getCompany();

                return form.data("title", "Manifold")
                .data("company", company)
                                .data("drivers", drivers)
                                .data("hasMessage", hasMessage)
                                .data("bootTrapDialogType", bootTrapDialogType)
                                .data("message", message)
                                .data("connectedUser", connectedUser)
                                .data("departure", departure)
                                .data("departureInfo", departureInfo)
                                .data("idtypes", idtypes)
                                .data("idtypesJsonString", idtypesJson.encode())
                                .data("departureDatetime", datetimeFormat.format(departure.getDateheure()))
                                .data("manifoldPlaceInfos", manifoldPlaceInfos)
                                .data("columns", departure.getVehicle().getVehicleplace().getNbcolumn())
                                .data("lines", departure.getVehicle().getVehicleplace().getNbligne())
                                .data("columnWidth",
                                                100 / departure.getVehicle().getVehicleplace().getNbcolumn())
        
                                ;*/

                return form.data("title", "Manifold");

        }

        @POST
        @Path("/form.html")
        @RolesAllowed({ "responsible" })
        @Produces(MediaType.TEXT_HTML)
        @Transactional
        public TemplateInstance postManifoldForm(@FormParam("depart") String idDepart,
                        @FormParam("carmatricule") String carmatricule,
                        @FormParam("driver1") String driver1,
                        @FormParam("driver2") String driver2,
                        @FormParam("manifolds") String rawManifolds) {


               /* Departure departure = Departure.find("iddeparture = " + idDepart).firstResult();
                departure.setCarmatricule(carmatricule);
                departure.setDriver(Strings.isEmptyOrNull(driver1) ? null
                                : Companyemployee.find("idcompanyemployee=" + driver1).firstResult());
                departure.setDriver2(Strings.isEmptyOrNull(driver2) ? null
                                : Companyemployee.find("idcompanyemployee=" + driver2).firstResult());
                departure.persistAndFlush();

                DeparturemanifoldPlaceInfo[] manifolds = parseManifolds(rawManifolds);

                Departuremanifold.delete("departure=" + idDepart);
                if (manifolds != null) {

                        for (int i = 0; i < manifolds.length; i++) {
                                DeparturemanifoldPlaceInfo adapter = manifolds[i];
                                Departuremanifold departuremanifold = null;

                                departuremanifold = new Departuremanifold();
                                departuremanifold.setIddeparturemanifold(null);
                                departuremanifold.setDeparture(
                                                departure);
                                departuremanifold.setPlacenumber(adapter.getPlace());
                                departuremanifold.setCivility(adapter.getCivility());
                                departuremanifold.setFname(adapter.getFname());
                                departuremanifold.setLname(adapter.getLname());
                                departuremanifold.setAddress(adapter.getAddress());
                                departuremanifold.setIdnumber(adapter.getIdnumber());
                                departuremanifold.setIdtype(
                                                adapter.getIdtype() != null
                                                                ? Idtype.find("ididtype = " + adapter.getIdtype())
                                                                                .firstResult()
                                                                : null);
                                try {
                                        departuremanifold.setIdissuedate(
                                                        Strings.isEmptyOrNull(adapter.getIdissuedate()) ? null
                                                                        : dateFormat.parse(adapter.getIdissuedate()));
                                } catch (ParseException e) {
                                        // TODO Auto-generated catch block
                                        e.printStackTrace();
                                }

                                departuremanifold.setIdissuelocation(adapter.getIdissuelocation());
                                try {
                                        departuremanifold.setIdduplicatadate(
                                                        Strings.isEmptyOrNull(adapter.getIdduplicatadate()) ? null
                                                                        : dateFormat.parse(
                                                                                        adapter.getIdduplicatadate()));
                                } catch (ParseException e) {
                                        // TODO Auto-generated catch block
                                        e.printStackTrace();
                                }

                                departuremanifold.setIdduplicatalocation(adapter.getIdduplicatalocation());
                                departuremanifold.setContactcivility(adapter.getContactcivility());
                                departuremanifold.setContactaddress(adapter.getContactaddress());
                                departuremanifold.setContactfname(adapter.getContactfname());
                                departuremanifold.setContactlname(adapter.getContactlname());
                                departuremanifold.setContactphone(adapter.getContactphone());
                                departuremanifold.setContactphone1(adapter.getContactphone1());

                                departuremanifold.setDaty(adapter.getDaty());

                                departuremanifold.persistAndFlush();

                        }
                }*/

                return this.manifoldForm(idDepart, "", "");
        }

       /* private static DeparturemanifoldPlaceInfo[] parseManifolds(String rawJson) {
                if (!Strings.isEmptyOrNull(rawJson)) {
                        try {
                                return mapper.readValue(rawJson, DeparturemanifoldPlaceInfo[].class);
                        } catch (JsonMappingException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                        } catch (JsonProcessingException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                        }
                }

                return null;
        }*/

       /* private List<DeparturemanifoldPlaceInfo> getManifoldPlaceInfos(Departure departure) {
                List<Departuremanifold> savedManifolds = Departuremanifold
                                .find("departure=" + departure.getIddeparture()).list();
                List<Booking> bookings = Booking.find("departure=" + departure.getIddeparture()).list();
                List<PlaceInfo> placeInfos = this.departApi.getPlaceInfoDeparture(departure.getIddeparture());

                List<DeparturemanifoldPlaceInfo> manifolds = new ArrayList<>();

                placeInfos.forEach((placeInfo) -> {

                        if (placeInfo.getPlace() == -2) {
                                manifolds.add(new DeparturemanifoldPlaceInfo(placeInfo, null, null, departure));

                        } else if (placeInfo.isOccuped()) {

                                Booking booking = bookings.stream().filter(b -> {
                                        return b.getBookingplaceCollection()
                                                        .stream().anyMatch(bp -> bp.getPlace() == placeInfo.getPlace());
                                }).findFirst().orElseGet(()-> {
                                        return null;
                                });

                                System.out.println(savedManifolds.size());        
                                Departuremanifold departuremanifold = savedManifolds.stream()
                                                .filter(sm ->  {                                                        
                                                        return sm.getPlacenumber()!=null && placeInfo.getPlace() == sm.getPlacenumber();
                                                
                                                })
                                                .findFirst().orElse(null);

                                manifolds.add(new DeparturemanifoldPlaceInfo(placeInfo, departuremanifold, booking,
                                                departure));
                        }

                        else {
                                manifolds.add(new DeparturemanifoldPlaceInfo(placeInfo, null, null,
                                                departure));

                        }

                });

                return manifolds;

        }*/

}
