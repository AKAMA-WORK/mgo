package com.mgo.resource;

import com.mgo.auth.AuthService;
import com.mgo.auth.AuthenticatedUser;
import com.mgo.entity.Booking;
import com.mgo.entity.BookingLine;
import com.mgo.entity.DepartureVehicleSeat;
import com.mgo.entity.OrganizationConfig;
import com.mgo.generator.TiketGenerator;
import com.mgo.model.booking.*;
import com.mgo.model.common.OffsetPaging;
import com.mgo.model.departure.DepartureSearchFilterModel;
import com.mgo.model.departure.DepartureSearchOptionsModel;
import com.mgo.model.departure.DepartureSearchResultModel;
import com.mgo.model.departure.DepartureSearchSortModel;
import com.mgo.service.booking.BookingService;
import com.mgo.service.booking.SeatAlreadyBooked;
import com.mgo.service.departure.DepartureService;
import com.mgo.socketio.SocketIoApplication;
import com.mgo.tracer.TraceLog;

import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Metered;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.Explode;
import org.eclipse.microprofile.openapi.annotations.enums.ParameterIn;
import org.eclipse.microprofile.openapi.annotations.enums.ParameterStyle;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.opentracing.Traced;
import org.jboss.logging.Logger;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import io.quarkus.arc.log.LoggerName;
import io.quarkus.security.Authenticated;
import io.vertx.core.json.Json;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.core.Response.Status;

import java.awt.image.BufferedImage;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

@Path("/bookings")
@Tag(name = "Booking API")
public class BookingApi {
    SimpleDateFormat referenceDateFormat = new SimpleDateFormat("yyMMdd.HH:mm:ss");

    @Inject
    DepartureApi departureApi;

    @Inject
    SocketIoApplication socketIoApplication;

    @LoggerName("booking")
    Logger bookingLogger;



    @GET
    @Path("/")
    @TraceLog
    @Traced(operationName = "/bookings")
    @Counted(name = "bookingsReadCount")
    @Metered(name = "bookingsReadMeter")
    @Timed(name = "bookingsReadTime")
    // @RolesAllowed({ "departure", "departure.readonly" })
    @Operation(operationId = "searchBookings", summary = "Search bookings")
    @APIResponse(responseCode = "200", content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(ref = "BookingSearchResult")))
    @APIResponse(responseCode = "401", ref = "Unauthorized")
    @APIResponse(responseCode = "403", ref = "Forbidden")
    @APIResponse(responseCode = "404", ref = "NotFound")
    @APIResponse(responseCode = "500", ref = "InternalError")
    public BookingSearchResultModel searchBookings(
            @Parameter(name = "q", description = "The search text", in = ParameterIn.QUERY, schema = @Schema(type = SchemaType.STRING))
            @QueryParam("q") String q,

            @Parameter(name = "paging", description = "The paging", in = ParameterIn.QUERY, schema = @Schema(ref = "OffsetPaging"), explode = Explode.TRUE, style = ParameterStyle.DEEPOBJECT)
            @QueryParam("paging")
            OffsetPaging paging,

            @Parameter(name = "filter", description = "The filter", in = ParameterIn.QUERY, schema = @Schema(ref = "BookingSearchFilter"), explode = Explode.TRUE, style = ParameterStyle.DEEPOBJECT)
            @QueryParam("filter")
            BookingSearchFilterModel filter,

            @Parameter(name = "sort", description = "The sorting", in = ParameterIn.QUERY, schema = @Schema(ref = "BookingSearchSort"),explode = Explode.TRUE, style = ParameterStyle.DEEPOBJECT)
            @QueryParam("sort")
            BookingSearchSortModel sort,


            @Parameter(name = "options", description = "The options", in = ParameterIn.QUERY, schema = @Schema(ref = "BookingSearchOptions"), explode = Explode.TRUE, style = ParameterStyle.DEEPOBJECT)
            @QueryParam("options")
            BookingSearchOptionsModel options,

            @Context UriInfo uriInfo,

            @Context SecurityContext securityContext



    ) throws com.mgo.qs.parser.ParseException {

        AuthenticatedUser user = AuthService.getAuthenticatedUser(securityContext);


        return BookingService.searchBookings(user,uriInfo);

    }

    @DELETE
    @Path("/{bookingId}")
    @TraceLog
    @Traced(operationName = "/{bookingId}")
    @Counted(name = "deleteBookingCount")
    @Metered(name = "deleteBookingMeter")
    @Timed(name = "deleteBookingTime")
    @Operation(operationId = "deleteBooking", summary = "Delete booking")
    @APIResponse(responseCode = "200")
    @APIResponse(responseCode = "401", ref = "Unauthorized")
    @APIResponse(responseCode = "403", ref = "Forbidden")
    @APIResponse(responseCode = "404", ref = "NotFound")
    @APIResponse(responseCode = "500", ref = "InternalError")
    @Authenticated
    public Object deleteBooking(
            @Parameter(name = "bookingId", description = "The id of booking", required = true, in = ParameterIn.PATH, schema = @Schema(type = SchemaType.STRING))
            @PathParam("bookingId") String bookingId,
            @Context SecurityContext securityContext

    ){
        Booking booking = Booking.findById( bookingId);
        if(booking==null){
            return Response.status(Status.NOT_FOUND).build();
        }
        AuthenticatedUser user= AuthService.getAuthenticatedUser(securityContext);

        BookingService.deleteBooking(user,booking);
        return Response.status(Status.OK).build();
    }



    @PUT
    @Path("/{bookingId}")
    @TraceLog
    @Traced(operationName = "/{bookingId}")
    @Counted(name = "bookingUpdateCount")
    @Metered(name = "bookingUpdateMeter")
    @Timed(name = "bookingUpdateTime")
    @RequestBody(ref = "BookingUpdate")
    @Operation(operationId = "updateBooking", summary = "Update Booking")
    @APIResponse(responseCode = "200", content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(ref = "Booking")))
    @APIResponse(responseCode = "401", ref = "Unauthorized")
    @APIResponse(responseCode = "403", ref = "Forbidden")
    @APIResponse(responseCode = "404", ref = "NotFound")
    @APIResponse(responseCode = "500", ref = "InternalError")
    @Produces(MediaType.APPLICATION_JSON)
    @Authenticated
    @Transactional
    public Object updateBooking(
            @Parameter(name = "bookingId", description = "The id of booking", required = true, in = ParameterIn.PATH, schema = @Schema(type = SchemaType.STRING))
            @PathParam("bookingId") String bookingId,
            BookingUpdateModel bookingUpdate,
            @Context SecurityContext securityContext
    ) throws ParseException {
        AuthenticatedUser user= AuthService.getAuthenticatedUser(securityContext);


        Booking booking = BookingService.updateBooking(user,bookingId,bookingUpdate);
        if(booking==null){
            return Response.status(Status.NOT_FOUND).build();
        }

        return new BookingModel(booking, new Date());
    }


    @POST
    @Path("/")
    @TraceLog
    @Traced(operationName = "/booking")
    @Counted(name = "bookingCreationCount")
    @Metered(name = "bookingCreationMeter")
    @Timed(name = "bookingCreationTime")
    @RequestBody(ref = "BookingCreate")
    @Operation(operationId = "createBooking", summary = "Create Booking")
    @APIResponse(responseCode = "200", content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(ref = "Booking")))
    @APIResponse(responseCode = "401", ref = "Unauthorized")
    @APIResponse(responseCode = "403", ref = "Forbidden")
    @APIResponse(responseCode = "500", ref = "InternalError")
    @APIResponse(responseCode = "409", content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(ref = "ConflictBookingResponse")))
    @Produces(MediaType.APPLICATION_JSON)
    @Authenticated
    @Transactional
    public Object createBooking(
            BookingCreateModel bookingCreate,
            @Context SecurityContext securityContext
            ) {

        AuthenticatedUser user= AuthService.getAuthenticatedUser(securityContext);

        Instant now = Instant.now();

        try {

            Date lockUntil =  new Date(now.plus(5, ChronoUnit.MINUTES).toEpochMilli());

            List<DepartureVehicleSeat> seats = BookingService.lockBookingSeats(
                    user,
                    bookingCreate,
                    now,
                    lockUntil
            );

            if(seats==null){
                return Response.status(Status.CONFLICT).build(); //Response.status( new ConflictBookingResponseModel(bookingCreate,new Date(now.toEpochMilli()))).build();
            }


           return new BookingModel(BookingService.createBooking(user,bookingCreate,seats,now, lockUntil),new Date(now.toEpochMilli()));

        }
        catch (SeatAlreadyBooked | ParseException booked){
            return new ConflictBookingResponseModel(bookingCreate,new Date(now.toEpochMilli()));
        }



      /*  Booking.getEntityManager().createNativeQuery("LOCK TABLES booking WRITE, bookingplace WRITE").getMaxResults();

        try {

            String inQuery = this.formatToInParameter(bookingCreate.getSeats());
            List<Bookingplace> bookedPlaces = Bookingplace
                    .list("place IN (" + inQuery + ") AND idbookingplace IN (SELECT idbooking FROM "
                            + Booking.class.getName() + " WHERE departure='"
                            + bookingCreate.getDepartureId() + "')");

            if (bookedPlaces.size() > 0) {
                // Conflict
                Departure departure = Departure.findById(bookingCreate.getDepartureId());
                return new ConflictBookingResponseModel(bookingCreate, bookedPlaces.stream().map(bp -> {
                    return bp.getPlace();
                }).collect(Collectors.toList()),
                        new DepartureModel(departure, departureApi.getDeparturePlaces(departure),
                                departureApi.getDepartureAvailablePlaces(departure)));

            }
            ;

            if (Strings.isEmptyOrNull(bookingCreate.getReference())
                    && !Strings.isEmptyOrNull(bookingCreate.getPhone())) {
                Date now = new Date();
                bookingCreate.setReference(
                        bookingCreate.getPhone() + "." + referenceDateFormat.format(now) + "." + now.getTime());
            }

            Departure departure = Departure.findById(bookingCreate.getDepartureId());
            Integer phoneToInt = Integer.valueOf(bookingCreate.getPhone().replaceAll("[^0-9.]", ""));
            Client client = Client.find("phone = " + phoneToInt).firstResult();
            if (client == null) {
                client = new Client();
                client.persistAndFlush();
            }
            client.setPhone(phoneToInt);
            client.setCivility(bookingCreate.getCivility());
            client.setFname(bookingCreate.getName().length() > 45 ? bookingCreate.getName().substring(0, 44)
                    : bookingCreate.getName());

            List<Integer> listPlaces = bookingCreate.getSeats();

            Booking booking = new Booking();
            booking.setDeparture(departure);
            booking.setClient(client);
            booking.setPaymentid(bookingCreate.getReference());
            booking.setPaymentmethod(Paymentmethod.findById(bookingCreate.getPaymentMethodId()));
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
            });

            return new BookingModel(booking, departureApi.getDeparturePlaces(departure),
                    departureApi.getDepartureAvailablePlaces(departure));

        } finally {
            Booking.getEntityManager().createNativeQuery("UNLOCK TABLES").getMaxResults();
        }*/

      //  return null;
    }

    private String formatToInParameter(List<Integer> listPlaces) {
        StringJoiner joiner = new StringJoiner(",");

        listPlaces.forEach(place -> {
            joiner.add(place.toString());
        });

        return joiner.toString();
    }
    @GET
    @Path("/{bookingId}")
    @TraceLog
    @Traced(operationName = "/{bookingId}")
    @Counted(name = "bookingCount")
    @Metered(name = "bookingMeter")
    @Timed(name = "bookingTime")
    @Operation(operationId = "booking", summary = "Get booking")
    @APIResponse(responseCode = "200", content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(ref = "Booking" )))
    @APIResponse(responseCode = "401", ref = "Unauthorized")
    @APIResponse(responseCode = "403", ref = "Forbidden")
    @APIResponse(responseCode = "404", ref = "NotFound")
    @APIResponse(responseCode = "500", ref = "InternalError")
    @Produces(MediaType.APPLICATION_JSON)
    @Authenticated
    public Object booking(
        @Parameter(name = "bookingId", description = "The id of booking", required = true, in = ParameterIn.PATH, schema = @Schema(type = SchemaType.STRING))
        @PathParam("bookingId") String bookingId
    ){
        Booking booking = Booking.findById( bookingId);
        if(booking==null){
            return Response.status(Status.NOT_FOUND).build();
        }

        return new BookingModel(booking,new Date());
    }



    @GET
    @Path("/{bookingId}/lines/{bookingLineId}/ticket.png")
    @TraceLog
    @Traced(operationName = "/{bookingId}/lines/{bookingLineId}/ticket")
    @Counted(name = "bookingTicketCount")
    @Metered(name = "bookingTicketMeter")
    @Timed(name = "bookingTicketTime")
    @Operation(operationId = "bookingTicket", summary = "Get booking ticket")
    @APIResponse(responseCode = "200", content = @Content(mediaType = "image/png", schema = @Schema(format = "binary" )))
    @APIResponse(responseCode = "401", ref = "Unauthorized")
    @APIResponse(responseCode = "403", ref = "Forbidden")
    @APIResponse(responseCode = "404", ref = "NotFound")
    @APIResponse(responseCode = "500", ref = "InternalError")
    @Produces("image/png")
    @Authenticated
    public Response bookingTicket(
        @Parameter(name = "bookingId", description = "The id of booking", required = true, in = ParameterIn.PATH, schema = @Schema(type = SchemaType.STRING))
        @PathParam("bookingId") String bookingId,
        @Parameter(name = "bookingLineId", description = "The id of booking line", required = true, in = ParameterIn.PATH, schema = @Schema(type = SchemaType.STRING))
        @PathParam("bookingLineId") String bookingLineId
    ){

        try {

            Booking booking = Booking.findById( bookingId);
            if(booking==null){
                return Response.status(Status.NOT_FOUND).build();
            }

            BookingLine line = booking.getLines().stream().filter(bookingLine -> Objects.equals(bookingLine.getBookingLineId(), bookingLineId)).findFirst().orElse(null);

            if(line==null){
                return Response.status(Status.NOT_FOUND).build();
            }
            OrganizationConfig companyconfig = OrganizationConfig.find("organization = " + line.getDeparture().getOrganization().getOrganizationId())
            .firstResult();

            if (booking != null) {
                return Response.ok(new TiketGenerator().generate(booking,line,companyconfig)).build();

            }

        } catch (Exception e) {
        }

        return Response.serverError().build(); 

    }

}