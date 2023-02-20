package com.mgo.service.person;

import com.mgo.auth.AuthenticatedUser;
import com.mgo.entity.Booking;
import com.mgo.entity.Person;
import com.mgo.model.booking.BookingModel;
import com.mgo.model.booking.BookingSearchFilterModel;
import com.mgo.model.booking.BookingSearchResultAggregateModel;
import com.mgo.model.booking.BookingSearchResultModel;
import com.mgo.model.departure.DepartureSearchFilterModel;
import com.mgo.model.person.PersonModel;
import com.mgo.model.person.PersonSearchFilterModel;
import com.mgo.model.person.PersonSearchResultModel;
import com.mgo.qs.parser.ParseException;
import com.mgo.querybuilder.BuildQueryResult;
import com.mgo.querybuilder.QueryBuilder;
import com.mgo.service.departure.AvailableSeatWhereConditionBuilder;
import com.mgo.service.departure.DateWhereConditionBuilder;

import javax.ws.rs.core.UriInfo;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class PersonService {

    private static final QueryBuilder<PersonSearchFilterModel> queryBuilder = new QueryBuilder<PersonSearchFilterModel>(null,null,"Person","p");

    public static PersonSearchResultModel searchPeople(
            AuthenticatedUser user,
            UriInfo uriInfo
    ) throws ParseException {
        PersonSearchResultModel resultModel = new PersonSearchResultModel();


        BuildQueryResult query =  queryBuilder.build(uriInfo, PersonSearchFilterModel.class);

        List<Person> bookings = Person.find(query.getSelectQuery(),query.getParameters())
                .range(query.getPaging().getOffset(),query.getPaging().getOffset()+ query.getPaging().getLimit()).list();
        long totalCount = Person.count(query.getCountQuery(),query.getParameters());

        Date now = new Date();

        List<PersonModel> items =   bookings.stream().map(PersonModel::new).collect(Collectors.toList());
        resultModel.setItems(items);
        resultModel.setTotalCount(totalCount);


        return resultModel;
    }
}
