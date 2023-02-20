package com.mgo.service.departure;

import com.mgo.auth.AuthenticatedUser;
import com.mgo.entity.*;
import com.mgo.model.booking.BookingCreateModel;
import com.mgo.model.common.SearchResultAggregateOrganizationModel;
import com.mgo.model.common.SearchResultAggregatePeriodOfDayModel;
import com.mgo.model.common.SearchResultAggregateVehicleTypeModel;
import com.mgo.model.departure.*;
import com.mgo.qs.parser.ParseException;
import com.mgo.querybuilder.BuildQueryResult;
import com.mgo.querybuilder.QueryBuilder;
import com.mgo.service.GroupByTotalCount;
import com.mgo.util.DateTimeFormat;
import com.mgo.util.PeriodOfDays;
import com.mgo.util.Strings;
import io.quarkus.panache.common.Parameters;

import javax.ws.rs.core.UriInfo;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

public class DepartureService {
   private static final QueryBuilder<DepartureSearchFilterModel> queryBuilder = new QueryBuilder<DepartureSearchFilterModel>(List.of(new AvailableSeatWhereConditionBuilder(), new DateWhereConditionBuilder(), new PeriodOfDaysWhereConditionBuilder()),  new HashMap<>(){{
       put("from","from_id_city");
       put("to","to_id_city");
       put("categoryId","category.categoryId");
       put("vehicleTypeId","vehicleType.vehicleTypeId");
   }},"Departure","d");


   public static void deleteDeparture(AuthenticatedUser user,Departure departure){

       // TODO : detect booked seats

       departure.delete();
   }

    public static List<Departure> bulkCreateDepartures(
            AuthenticatedUser user,
            DepartureBulkCreateModel departureBulkCreate
    ) throws java.text.ParseException {
          List<Departure> departures = new ArrayList<>();
          String [] dateParts =   departureBulkCreate.getDate().split(" ");
            Date dateStart = DateTimeFormat.parseDate(dateParts[0]);
            Date dateEnd = dateParts.length==2 ? DateTimeFormat.parseDate(dateParts[1]): dateStart;

        Calendar start = Calendar.getInstance();
        start.setTime(dateStart);
        Calendar end = Calendar.getInstance();
        end.setTime(dateEnd);
        end.add(Calendar.DATE, 1);

        for (Date date = start.getTime(); start.before(end); start.add(Calendar.DATE,
                1), date = start.getTime()) {
            for (String time : departureBulkCreate.getTime()) {
                 String[] timeParts = time.split(" ");

                for (DepartureBulkCreateVehicleModel departureBulkCreateVehicleModel : departureBulkCreate.getVehicles()){
                    DepartureCreateModel createModel = new DepartureCreateModel();
                    createModel.setCategoryId(departureBulkCreate.getCategoryId());
                    createModel.setDate(DateTimeFormat.formatDate(date));
                    createModel.setFrom(departureBulkCreate.getFrom());
                    createModel.setTo(departureBulkCreate.getTo());
                    createModel.setPrice(departureBulkCreate.getPrice());
                    createModel.setOrganizationId(departureBulkCreate.getOrganizationId());
                    createModel.setTime(timeParts[0]);
                    if(timeParts.length==2){
                        createModel.setEndTime(timeParts[1]);
                        createModel.setEndDate(createModel.getDate());
                    }
                    createModel.setVehicleId(departureBulkCreateVehicleModel.getVehicleId());
                    createModel.setVehicleModelId(departureBulkCreateVehicleModel.getVehicleModelId());
                    createModel.setColumns(departureBulkCreateVehicleModel.getColumns());
                    createModel.setLines(departureBulkCreateVehicleModel.getLines());
                    createModel.setSeats(departureBulkCreateVehicleModel.getSeats());

                    departures.add(createDeparture(user,createModel));
                }
            }

        }


          return departures;


    }
    public static Departure createDeparture(
            AuthenticatedUser user,
            DepartureCreateModel departureCreate
    ) throws java.text.ParseException {

        Departure departure = new Departure();
        departure.setCreatedBy(user.getPerson());
        departure.setDateTime(DateTimeFormat.parseDatetime(departureCreate.getDate(),departureCreate.getTime()));
        departure.setEndDateTime(DateTimeFormat.parseDatetime(departureCreate.getEndDate(),departureCreate.getEndTime()));
        departure.setPrice(departureCreate.getPrice());
        departure.setCategory(departureCreate.getCategoryId()!=null? Category.findById(departureCreate.getCategoryId()) : null);
        departure.setFrom(City.findById(departureCreate.getFrom()));
        departure.setTo(City.findById(departureCreate.getTo()));
        departure.setOrganization(Organization.findById(departureCreate.getOrganizationId()));
        departure.setStatus(DepartureStatus.ON_DATE);

        if(departureCreate.getVehicleId()!=null){
            Vehicle vehicle = Vehicle.findById(departureCreate.getVehicleId());
            departure.setVehicle(vehicle);
            departure.setColumns(vehicle.getColumns());
            departure.setLines(vehicle.getLines());
            departure.setCarRegistrationNumber(vehicle.getCarRegistrationNumber());
            departure.setVehicleType(vehicle.getVehicleType());
            departure.persistAndFlush();

            vehicle.getSeats().forEach(seat -> {
                DepartureVehicleSeat vehicleSeat = new DepartureVehicleSeat();
                vehicleSeat.setCreatedBy(user.getPerson());
                vehicleSeat.setX(seat.getX());
                vehicleSeat.setY(seat.getY());
                vehicleSeat.setSeatNumber(seat.getSeatNumber());
                vehicleSeat.setStatus(DepartureVehicleSeatStatus.AVAILABLE);
                vehicleSeat.persistAndFlush();
            });

        }
        else if(departureCreate.getVehicleModelId()!=null){
              VehicleModel vehicleModel = VehicleModel.findById(departureCreate.getVehicleModelId());
              departure.setColumns(vehicleModel.getColumns());
              departure.setLines(vehicleModel.getLines());
              departure.setVehicleType(departureCreate.getVehicleTypeId()!=null? VehicleType.findById(departureCreate.getVehicleTypeId()) :null);
              departure.persistAndFlush();

              vehicleModel.getSeats().forEach(seat -> {
                  DepartureVehicleSeat vehicleSeat = new DepartureVehicleSeat();
                  vehicleSeat.setCreatedBy(user.getPerson());
                  vehicleSeat.setX(seat.getX());
                  vehicleSeat.setY(seat.getY());
                  vehicleSeat.setSeatNumber(seat.getSeatNumber());
                  vehicleSeat.setStatus(DepartureVehicleSeatStatus.AVAILABLE);
                  vehicleSeat.persistAndFlush();
              });

        }

        else if(departureCreate.getColumns()!=null && departureCreate.getLines()!=null

        && departureCreate.getSeats()!=null){
                departure.setColumns(departureCreate.getColumns());
                departure.setLines(departureCreate.getLines());
                departure.setVehicleType(departureCreate.getVehicleTypeId()!=null? VehicleType.findById(departureCreate.getVehicleTypeId()) :null);
                departure.persistAndFlush();

            departureCreate.getSeats().forEach(seat -> {
                DepartureVehicleSeat vehicleSeat = new DepartureVehicleSeat();
                vehicleSeat.setCreatedBy(user.getPerson());
                vehicleSeat.setX(seat.getX());
                vehicleSeat.setY(seat.getY());
                vehicleSeat.setSeatNumber(seat.getSeatNumber());
                vehicleSeat.setStatus(DepartureVehicleSeatStatus.AVAILABLE);
                vehicleSeat.persistAndFlush();
            });

        }
        else {
            throw new RuntimeException("No seat");
        }



        return  departure;
    }
    public static DepartureSearchResultModel searchDepartures(
            AuthenticatedUser user,
            UriInfo uriInfo
    ) throws ParseException {
        DepartureSearchResultModel resultModel = new DepartureSearchResultModel();


        BuildQueryResult query =  queryBuilder.build(uriInfo,DepartureSearchFilterModel.class);

        List<Departure> departures = Departure.find(query.getSelectQuery(),query.getParameters())
                .range(query.getPaging().getOffset(),query.getPaging().getOffset()+ query.getPaging().getLimit()).list();
        long totalCount = Departure.count(query.getCountQuery(),query.getParameters());

        Date now = new Date();

        List<DepartureModel> items =   departures.stream().map(departure -> new DepartureModel(departure, findDepartureOrganizationConfig(departure), now) ).collect(Collectors.toList());
        resultModel.setItems(items);
        resultModel.setTotalCount(totalCount);

        DepartureSearchResultAggregateModel aggregateResult = new DepartureSearchResultAggregateModel();
        aggregateResult.setOrganizations(getAggregateOrganization(query));
        aggregateResult.setPeriodOfDays(getAggregatePeriodOfDays(query));
        aggregateResult.setVehicleTypes(getAggregateVehicleType(query));


        resultModel.setAggregate(aggregateResult);




        return resultModel;
    }


    private static long countPeriodOfDay( BuildQueryResult query, String startHour, String endHour){

        String whereCondition = "(TIME(date_time) BETWEEN'"+startHour+"' AND '"+endHour+"')";
        String whereQuery = query!=null ? query.getQuery() :null;
        if(whereQuery!=null){
            whereCondition = whereCondition+" AND "+whereQuery;
        }

        return Departure.count(whereCondition,query!=null? query.getParameters():null);
    }
    private static List<SearchResultAggregatePeriodOfDayModel> getAggregatePeriodOfDays(
            BuildQueryResult query
    ){


       return PeriodOfDays.getAllPeriodOfDays().stream().map(p-> new  SearchResultAggregatePeriodOfDayModel(p.getId(),p.getLabel(),p.getStartTime(),p.getEndTime(),countPeriodOfDay(query,p.getStartTime(),p.getEndTime()))).collect(Collectors.toList());




    }

    private static List<SearchResultAggregateOrganizationModel> getAggregateOrganization(
            BuildQueryResult query
    ){

        String whereQuery = query!=null ? query.getQuery() :null;
        String whereCondition = whereQuery!=null ? " where "+whereQuery :"";

         List<GroupByTotalCount> aggregateResult =  Departure.find("SELECT d.organization.organizationId, count(*) as total_count from Departure d "+whereCondition+" GROUP BY d.organization.organizationId ORDER BY total_count DESC",query!=null? query.getParameters():null)
                .project(GroupByTotalCount.class).list();

        List<Organization> organizations = Organization.list("order by name");  //Organization.list("id_organization IN ?1",aggregateResult.stream().map(GroupByTotalCount::getId).collect(Collectors.toList()));

        return  organizations.stream().map(t-> {

            GroupByTotalCount count = aggregateResult.stream().filter(c->c.getId().equals(t.getOrganizationId())).findFirst().orElse(null);
            return  new SearchResultAggregateOrganizationModel( count!=null? count.getTotalCount():0 ,t);

        }).sorted((t1,t2)-> Long.compare(t2.getTotalCount(),t1.getTotalCount())).collect(Collectors.toList());

    }


    private static List<SearchResultAggregateVehicleTypeModel> getAggregateVehicleType(
            BuildQueryResult query
    ){



        String whereQuery = query!=null ? query.getQuery() :null;
        String whereCondition = whereQuery!=null ? " where "+whereQuery :"";

        List<GroupByTotalCount> aggregateResult =  Departure.find("SELECT vehicleType.vehicleTypeId, count(*) as total_count from Departure "+whereCondition+" GROUP BY vehicleType.vehicleTypeId ORDER BY total_count DESC",query!=null? query.getParameters():null)
                .project(GroupByTotalCount.class).list();

        List<VehicleType> vehicleTypes = VehicleType.list("order by name"); // VehicleType.list("id_vehicle_type IN ?1",aggregateResult.stream().map(GroupByTotalCount::getId).collect(Collectors.toList()));

        return  vehicleTypes.stream().map(t-> {

            GroupByTotalCount count = aggregateResult.stream().filter(c->c.getId().equals(t.getVehicleTypeId())).findFirst().orElse(null);
        return  new SearchResultAggregateVehicleTypeModel( count!=null? count.getTotalCount():0 ,t);

    }).sorted((t1,t2)-> Long.compare(t2.getTotalCount(),t1.getTotalCount())).collect(Collectors.toList());
    }

    public static OrganizationConfig findDepartureOrganizationConfig(Departure departure){
        if(departure==null){
            return null;
        }

        String query = "id_organization= :pOrganizationId AND from_id_city= :pFrom AND to_id_city= :pTo";


        if(departure.getCategory()!=null){
              String queryWithCategory =  query+" AND id_category= :pCategoryId";

            OrganizationConfig config = OrganizationConfig.find(queryWithCategory,Parameters.with("pOrganizationId",departure.getOrganization().getOrganizationId())
                    .and("pFrom",departure.getFrom().getCityId())
                    .and("pTo",departure.getTo().getCityId())
                    .and("pCategoryId",departure.getCategory().getCategoryId())
            ).firstResult();
            if(config!=null){
                return config;
            }
        }
        OrganizationConfig config = OrganizationConfig.find(query,Parameters.with("pOrganizationId",departure.getOrganization().getOrganizationId())
                .and("pFrom",departure.getFrom().getCityId())
                .and("pTo",departure.getTo().getCityId())).firstResult();
        if(config!=null){
            return config;
        }


        return OrganizationConfig.find("id_organization= :pOrganizationId",Parameters.with("pOrganizationId",departure.getOrganization().getOrganizationId())).firstResult();

    }


    public static String getEstimatedArrivalTime(Departure departure){
        //TODO:
        return null;
    }


}
