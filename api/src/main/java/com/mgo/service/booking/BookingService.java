package com.mgo.service.booking;

import com.mgo.auth.AuthenticatedUser;
import com.mgo.entity.*;
import com.mgo.model.booking.*;
import com.mgo.model.common.SearchResultAggregateCityModel;
import com.mgo.model.common.SearchResultAggregateOrganizationModel;
import com.mgo.model.departure.*;
import com.mgo.qs.parser.ParseException;
import com.mgo.querybuilder.BuildQueryResult;
import com.mgo.querybuilder.QueryBuilder;
import com.mgo.service.GroupByTotalCount;
import com.mgo.util.DateTimeFormat;
import io.quarkus.panache.common.Parameters;
import io.vertx.core.json.Json;

import javax.ws.rs.core.UriInfo;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

public class BookingService {
    private static final int MAX_ATTEMPTS_COUNT = 5;

    private static final QueryBuilder<BookingSearchFilterModel> queryBuilder = new QueryBuilder<>(List.of(
            new BookingWhereConditionBuilder("from"),
            new BookingWhereConditionBuilder("to"),
            new BookingWhereConditionBuilder("organization")
    ), new HashMap<>() {{
        put("from", "from.cityId");
        put("to", "to.cityId");
        put("bookingDate","b.creationDate");
        put("clientIdPerson","b.client.personId");
    }},"Booking","b");


    public static BookingSearchResultModel searchBookings(
            AuthenticatedUser user,
            UriInfo uriInfo
    ) throws ParseException {
        BookingSearchResultModel resultModel = new BookingSearchResultModel();


         BuildQueryResult queryResult =  queryBuilder.build(uriInfo, BookingSearchFilterModel.class);

         List<Booking> bookings = Booking.find(queryResult.getSelectQuery(),queryResult.getParameters())
                 .range(queryResult.getPaging().getOffset(),queryResult.getPaging().getOffset()+ queryResult.getPaging().getLimit()).list();


        long totalCount = Booking.count(queryResult.getCountQuery(),queryResult.getParameters());
        Date now = new Date();

        List<BookingModel> items =   bookings.stream().map(booking -> new BookingModel(booking,  now) ).collect(Collectors.toList());
        resultModel.setItems(items);
        resultModel.setTotalCount(totalCount);



        BookingSearchResultAggregateModel aggregateResult = new BookingSearchResultAggregateModel();
        aggregateResult.setOrganizations(getAggregateOrganization(queryResult));
        aggregateResult.setFrom(getAggregateCity(queryResult,"from"));
        aggregateResult.setTo(getAggregateCity(queryResult,"to"));
       // aggregateResult.setPeriodOfDays(getAggregatePeriodOfDays(query));
       // aggregateResult.setVehicleTypes(getAggregateVehicleType(query));


        resultModel.setAggregate(aggregateResult);


        return resultModel;
    }


    private static List<SearchResultAggregateOrganizationModel> getAggregateOrganization(
            BuildQueryResult query
    ){

        String whereQuery = query!=null ? query.getQuery() :null;
        String whereCondition = whereQuery!=null ? " where "+whereQuery :"";

        List<GroupByTotalCount> aggregateResult =  Booking.find("SELECT d.organization.organizationId, count(*) as total_count from Booking b INNER JOIN b.lines bl INNER JOIN bl.departure d "+whereCondition+" GROUP BY d.organization.organizationId ORDER BY total_count DESC",query!=null? query.getParameters():null)
                .project(GroupByTotalCount.class).list();
        List<Organization> organizations = Organization.list("order by name");  //Organization.list("id_organization IN ?1",aggregateResult.stream().map(GroupByTotalCount::getId).collect(Collectors.toList()));

        return  organizations.stream().map(t-> {

            GroupByTotalCount count = aggregateResult.stream().filter(c->c.getId().equals(t.getOrganizationId())).findFirst().orElse(null);
            return  new SearchResultAggregateOrganizationModel( count!=null? count.getTotalCount():0 ,t);

        }).sorted((t1,t2)-> Long.compare(t2.getTotalCount(),t1.getTotalCount())).collect(Collectors.toList());

    }


    private static List<SearchResultAggregateCityModel> getAggregateCity(
            BuildQueryResult query,
            String field
    ){

        String whereQuery = query!=null ? query.getQuery() :null;
        String whereCondition = whereQuery!=null ? " where "+whereQuery :"";

        List<GroupByTotalCount> aggregateResult =  Booking.find("SELECT d."+field+".cityId, count(*) as total_count from Booking b INNER JOIN b.lines bl INNER JOIN bl.departure d "+whereCondition+" GROUP BY d."+field+".cityId ORDER BY total_count DESC",query!=null? query.getParameters():null)
                .project(GroupByTotalCount.class).list();
        List<City> cities = City.list("order by name");

        return  cities.stream().map(t-> {

            GroupByTotalCount count = aggregateResult.stream().filter(c->c.getId().equals(t.getCityId())).findFirst().orElse(null);
            return  new SearchResultAggregateCityModel( count!=null? count.getTotalCount():0 ,t);

        }).sorted((t1,t2)-> Long.compare(t2.getTotalCount(),t1.getTotalCount())).collect(Collectors.toList());

    }



    public static void deleteBooking(
            AuthenticatedUser user,
            Booking booking
    ){
        updateVehicleSeatStatus(user,booking,"UNLOCKED");
        booking.delete();
    }
    public static Booking updateBooking(
            AuthenticatedUser user,
            String bookingId,
            BookingUpdateModel bookingUpdate
    ) throws java.text.ParseException {

        Booking booking = Booking.findById(bookingId);
        if(booking==null){
            return  null;
        }

        booking.setUpdatedBy(user.getPerson());
        booking.setStatus(bookingUpdate.getStatus());
        booking.setStatusDate(new Date());
        booking.setPaymentId(bookingUpdate.getPaymentId());
        booking.setPaymentMethod(bookingUpdate.getPaymentMethod());
        booking.setPaymentDate(DateTimeFormat.parseDatetime(bookingUpdate.getPaymentDate()));
        booking.persistAndFlush();

        booking.setLines(booking.getLines().stream().map(bookingLine -> updateBookingLine(user,bookingLine,booking.getStatus())).collect(Collectors.toList()));
        updateVehicleSeatStatus(user, booking,booking.getStatus());

        return  booking;
    }

    private static BookingLine updateBookingLine(
            AuthenticatedUser user,
            BookingLine line,
            String status
    ){

        line.setStatus(status);
        line.setUpdatedBy(user.getPerson());
        line.persistAndFlush();

       line.setSeats(line.getSeats().stream().peek(lineSeat -> {
             lineSeat.setStatus(getBookingLineSeatStatusFromBookingLineStatus(line));
             lineSeat.persistAndFlush();
       }).collect(Collectors.toList()));

       return line;
    }


    private static String getBookingLineSeatStatusFromBookingLineStatus(BookingLine bookingLine){
        return bookingLine.getStatus().equals(BookingStatus.INITIATED)? BookingLineSeatStatus.LOCKED: bookingLine.getStatus().equals(BookingStatus.CONFIRMED)? BookingLineSeatStatus.BOOKED: BookingLineSeatStatus.CANCELLED;

    }


    public static Booking createBooking(
            AuthenticatedUser user,
            BookingCreateModel bookingCreate,
            List<DepartureVehicleSeat> seats,
            Instant now,
            Date lockUntil
    ) throws java.text.ParseException {

        Booking booking = new Booking();
        booking.setCreatedBy(user.getPerson());
        booking.setDate(new Date());
        booking.setAmount(bookingCreate.getLines().stream().map(BookingService::computeBookingLineAmount).reduce((double) 0,Double::sum));
        booking.setPaymentId(bookingCreate.getPaymentId());
        booking.setPaymentMethod(bookingCreate.getPaymentMethod());
        booking.setPaymentDate(DateTimeFormat.parseDatetime(bookingCreate.getPaymentDate()));
        booking.setClient(Person.findById(bookingCreate.getClientIdPerson()));
        booking.setStatus(bookingCreate.getStatus());
        booking.setStatusDate(new Date());
        booking.setWaitConfirmUntil(lockUntil);
        booking.persistAndFlush();

        List<BookingLine> createdLines =  bookingCreate.getLines().stream().map(lineCreateModel -> createBookingLine(user, booking,lineCreateModel,booking.getStatus() )).collect(Collectors.toList());
        booking.setLines(createdLines);

        updateVehicleSeatStatus(user, booking,booking.getStatus());
        return  booking;
    }

    private static void updateVehicleSeatStatus(
            AuthenticatedUser user,
            Booking booking,
            String status
    ){

        booking.getLines().forEach(bookingLine -> {
            updateBookingLineVehicleSeatStatus(user, booking,bookingLine,status);
        });


    }

    private static void updateBookingLineVehicleSeatStatus(
            AuthenticatedUser user,
            Booking booking,
            BookingLine bookingLine,
            String status

    ){

        if(status.equals(BookingStatus.INITIATED)){
            return;
        }

        Departure departure = bookingLine.getDeparture();

        bookingLine.getSeats().forEach(bookingLineSeat -> {

            DepartureVehicleSeat vehicleSeat = DepartureVehicleSeat.findBySeatNumber(departure.getDepartureId(),bookingLineSeat.getSeatNumber());
            if(vehicleSeat.getLockedByPerson()==null
            || vehicleSeat.getLockedByPerson().getPersonId().equals(booking.getClient().getPersonId())
            ){

                if(status.equals(BookingStatus.CONFIRMED)){
                    vehicleSeat.setStatus(DepartureVehicleSeatStatus.BOOKED);
                }
                else if(status.equals(BookingStatus.CANCELLED) || status.equals("UNLOCKED")){
                    vehicleSeat.setStatus(DepartureVehicleSeatStatus.AVAILABLE);
                    vehicleSeat.setLockUntil(null);
                    vehicleSeat.setLockedByPerson(null);
                }
                vehicleSeat.setUpdatedBy(user.getPerson());
                vehicleSeat.persistAndFlush();

            }
            /*else {
                // TODO : Logs conflicts


            }*/


        });

    }


    private static BookingLine createBookingLine(
            AuthenticatedUser user,
            Booking booking,
            BookingLineCreateModel lineCreate,
            String status
    ){
            Departure departure = Departure.findById(lineCreate.getDepartureId());
           BookingLine line = new BookingLine();
           line.setBooking(booking);
           line.setAmount(computeBookingLineAmount(lineCreate));
           line.setDeparture(Departure.findById(lineCreate.getDepartureId()));
           line.setStatus(status);
           line.setPrice(departure.getPrice());
           line.persistAndFlush();
            List<BookingLineSeat> seats = lineCreate.getSeats().stream().map(seatNumber -> createBookingLineSeat(user,line,seatNumber,getBookingLineSeatStatusFromBookingLineStatus(line))).collect(Collectors.toList());
            line.setSeats(seats);

           return line;
    }
    private static BookingLineSeat createBookingLineSeat(
            AuthenticatedUser user,
            BookingLine line,
            Integer seatNumber,
            String status
    ){

        BookingLineSeat seat = new BookingLineSeat();
        seat.setBookingLine(line);
        seat.setSeatNumber(seatNumber);
        seat.setStatus(status);
        seat.setCreatedBy(user.getPerson());

        seat.persistAndFlush();


        return seat;
    }


    private static Double computeBookingLineAmount (BookingLineCreateModel createModel){
        Departure departure =   Departure.findById(createModel.getDepartureId());
         return departure.getPrice()*createModel.getSeats().size();
    }


    public static List<DepartureVehicleSeat> lockBookingSeats (
            AuthenticatedUser user,
            BookingCreateModel bookingCreate,
            Instant now,
            Date lockUntil
    ) {

      if(bookingCreate.getLines().size()==1){
          BookingLineCreateModel line = bookingCreate.getLines().get(0);
          return  lockBookingLineSeats(user,bookingCreate,line,now,lockUntil);
      }

      List<DepartureVehicleSeat> seats = new ArrayList<>();
      List<BookingLineCreateModel> lineCreateModels = new ArrayList<>();

      for(int i=0; i<bookingCreate.getLines().size();i++){
          BookingLineCreateModel lineCreateModel = bookingCreate.getLines().get(i);
          List<DepartureVehicleSeat> vehicleSeats =  lockBookingLineSeats(user, bookingCreate, lineCreateModel,now,lockUntil);
          if(vehicleSeats!=null){
              seats.addAll(vehicleSeats);
              lineCreateModels.add(lineCreateModel);
          }
          else {
              seats.forEach(seat -> releaseSeat(seat.getDepartureVehicleSeatId(), bookingCreate.getClientIdPerson()));
              lineCreateModels.forEach(lineCModel-> releaseDeparture(lineCModel.getDepartureId(), bookingCreate.getClientIdPerson()));

              return null; //seats;
          }

      }

     return seats;

    }

    private static List<DepartureVehicleSeat> lockBookingLineSeats (
            AuthenticatedUser user,
            BookingCreateModel bookingCreate,
            BookingLineCreateModel line,
            Instant now,
            Date lockUntil
    ) {
        if(line.getSeats().size()==1){
            // One seat : Lock seat
            DepartureVehicleSeat seat = lockSeat(line.getDepartureId(),line.getSeats().get(0),bookingCreate.getClientIdPerson(),now,lockUntil);

            return Collections.singletonList(seat);
        }

        Departure lockedDeparture = lockDepartureWithAttempts(line.getDepartureId(), bookingCreate.getClientIdPerson(),now,lockUntil);
        List<DepartureVehicleSeat> bookedSeats = new ArrayList<>();
        try{
            if(lockedDeparture!=null) {
                for(int i=0;i<line.getSeats().size();i++){
                    DepartureVehicleSeat seat = lockSeatWithAttempts(line.getDepartureId(), line.getSeats().get(i),bookingCreate.getClientIdPerson(),now,lockUntil);
                    if(seat!=null){
                        bookedSeats.add(seat);
                    }
                    else {
                        bookedSeats.forEach(bookedSeat ->  releaseSeat(bookedSeat.getDepartureVehicleSeatId(), bookingCreate.getClientIdPerson()));
                        return null;
                    }
                }
            }
            return bookedSeats;

        }
        finally {
            if(lockedDeparture!=null){
                releaseDeparture(lockedDeparture.getDepartureId(), bookingCreate.getClientIdPerson());
            }
        }
    }




    public static DepartureVehicleSeat lockSeatWithAttempts(
            String departureId,
            Integer seatNumber,
            String clientIdPerson,
            Instant now,
            Date lockUntil
    ){
        for (int i=0;i<MAX_ATTEMPTS_COUNT;i++){
            try {
                DepartureVehicleSeat seat = lockSeat(departureId, seatNumber, clientIdPerson,now,lockUntil);
                if (seat != null) {
                    return seat;
                }
            }
            catch (SeatAlreadyBooked ignored){

            }

        }

        return null;
    }



    public static Departure lockDepartureWithAttempts(
           String departureId,
           String clientIdPerson,
           Instant now,
           Date lockUntil
    ){
        for (int i=0;i<MAX_ATTEMPTS_COUNT;i++){
            Departure departure = Departure.findById(departureId);
            boolean succeed = tryLockDeparture(departure,clientIdPerson,now,lockUntil);

            if(succeed){
                return  Departure.findById(departureId);
            }

        }

        return null;
    }

    private static boolean tryLockDeparture(Departure departure,
                                            String lockedByIdPerson,
                                            Instant now,
                                            Date lockUntil
      ){
        if(departure.getLockUntil()!=null && now.isBefore(departure.getLockUntil().toInstant())){
            return  false;
        }

        int result = Departure.lockDeparture(departure.getDepartureId(),new Date(now.plus(10,ChronoUnit.SECONDS).toEpochMilli()),lockedByIdPerson,departure.getVersion()+1);

        return result!=0;
    }

    private static void  releaseDeparture(String departureId, String clientIdPerson){
        Departure.releaseDeparture(departureId,clientIdPerson);
    }


    private static void releaseSeat(String departureVehicleSeatId,String clientIdPerson){
        DepartureVehicleSeat.releaseSeat(departureVehicleSeatId,clientIdPerson);
    }


    private static boolean isLockedDepartureByOtherPerson(String departureId, String clientIdPerson){
         Departure departure = Departure.findById(departureId);

        Instant now = Instant.now();
        if(departure.getLockUntil()==null){
            return  false;
        }

        if(now.isBefore(departure.getLockUntil().toInstant())){
            // LOCKED

            if(departure.getLockedByPerson()==null){
                return false;
            }
            return !Objects.equals(departure.getLockedByPerson().getPersonId(), clientIdPerson);
        }


        return  false;

    }



    public static DepartureVehicleSeat lockSeat(
            String departureId,
            Integer seatNumber,
            String clientIdPerson,
            Instant now,
            Date newLockUntil
    ){
        if(isLockedDepartureByOtherPerson(departureId, clientIdPerson)){

            throw  new SeatAlreadyBooked(departureId,seatNumber);
        }

        DepartureVehicleSeat seat = DepartureVehicleSeat.findBySeatNumber(departureId,seatNumber);

        Instant lockUntil = seat.getLockUntil()!=null ?  seat.getLockUntil().toInstant():null;


        if (!Objects.equals(seat.getStatus(), DepartureVehicleSeatStatus.AVAILABLE) || (lockUntil!=null && now.isBefore(lockUntil))){
                throw  new SeatAlreadyBooked(departureId,seatNumber);
        }



        int result = DepartureVehicleSeat.lockSeat(seat.getDepartureVehicleSeatId() ,newLockUntil,
                    clientIdPerson,
                seat.getVersion()+1
                );


        if(result==0){
            throw  new SeatAlreadyBooked(departureId,seatNumber);
        }

        return  DepartureVehicleSeat.findBySeatNumber(departureId, seatNumber);
    }
}
