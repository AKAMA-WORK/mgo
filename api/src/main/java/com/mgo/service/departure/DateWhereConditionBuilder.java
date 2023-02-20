package com.mgo.service.departure;

import com.mgo.querybuilder.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DateWhereConditionBuilder extends DefaultWhereConditionBuilder {
    @Override
    public List<String> getSupportedFields() {
        return List.of("date");
    }

    @Override
    protected String getColumnName(Map<String, String> columnsMapping, String field) {
        return "DATE(date_time)";
    }


    @Override
    protected WhereBuilderResult buildComparison(WhereBuilder builder, Map<String,String> columnsMapping, String op, String field, Object value,String from, String alias){
      //  String pName = getParameterName(field);
        String cName = getColumnName(columnsMapping,field);
        return new WhereBuilderResult(cName+""+ SqlComparisonBuilder.DEFAULT_COMPARISON_MAP.get(op)+" '"+value+"'",null);
    }




}
