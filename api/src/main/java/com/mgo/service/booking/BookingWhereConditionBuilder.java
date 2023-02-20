package com.mgo.service.booking;

import com.mgo.querybuilder.DefaultWhereConditionBuilder;
import com.mgo.querybuilder.SqlComparisonBuilder;
import com.mgo.querybuilder.WhereBuilder;
import com.mgo.querybuilder.WhereBuilderResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookingWhereConditionBuilder extends DefaultWhereConditionBuilder {
    private final String field;

    public BookingWhereConditionBuilder(String field) {
        this.field = field;
    }

    @Override
    public List<String> getSupportedFields() {
        return List.of(this.field);
    }

    @Override
    protected WhereBuilderResult buildComparison(WhereBuilder builder, Map<String,String> columnsMapping, String op, String field, Object value,String from, String alias){
        String cName = getColumnName(columnsMapping,field);
        String pName = getParameterName(field);

        if(op.equals("in") || op.equals("nin")){
            return new WhereBuilderResult(alias+".bookingId IN (SELECT bl.booking.bookingId FROM BookingLine bl WHERE bl.departure."+cName+" "+SqlComparisonBuilder.DEFAULT_COMPARISON_MAP.get(op)+" ("+this.buildListValue((Object[]) getParameterValue(value),true)+"))" ,null);
        }


        return new WhereBuilderResult( alias+".bookingId IN (SELECT bl.booking.bookingId FROM BookingLine bl WHERE bl.departure."+cName+" "+SqlComparisonBuilder.DEFAULT_COMPARISON_MAP.get(op)+" :"+pName+")",new HashMap<>(){{
            put(pName,value);
        }});
    }




}
