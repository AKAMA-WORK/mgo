package com.mgo.service.departure;

import com.mgo.querybuilder.WhereBuilder;
import com.mgo.querybuilder.WhereBuilderResult;
import com.mgo.querybuilder.WhereConditionBuilder;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class AvailableSeatWhereConditionBuilder implements WhereConditionBuilder {

    @Override
    public List<String> getSupportedFields() {
        return List.of("availableSeats");
    }

    @Override
    public WhereBuilderResult build(WhereBuilder builder, Map<String,String> columnsMapping, String op, String field, Object value, String from, String alias) {
        return null;
    }
}
