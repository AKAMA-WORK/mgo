package com.mgo.service.departure;

import com.mgo.model.common.PeriodOfDayModel;
import com.mgo.querybuilder.DefaultWhereConditionBuilder;
import com.mgo.querybuilder.SqlComparisonBuilder;
import com.mgo.querybuilder.WhereBuilder;
import com.mgo.querybuilder.WhereBuilderResult;
import com.mgo.util.PeriodOfDays;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class PeriodOfDaysWhereConditionBuilder extends DefaultWhereConditionBuilder {
    @Override
    public List<String> getSupportedFields() {
        return List.of("periodOfDays");
    }

    @Override
    protected String getColumnName(Map<String, String> columnsMapping, String field) {
        return "TIME(date_time)";
    }


    @Override
    protected WhereBuilderResult buildComparison(WhereBuilder builder, Map<String,String> columnsMapping, String op, String field, Object value,String from, String alias){

        if(value==null){
            return null;
        }

        List<String> periodOfDays = Arrays.stream(((Object[]) value)).map(Object::toString).collect(Collectors.toList());

        List<PeriodOfDayModel> filteredPeriods = PeriodOfDays.getAllPeriodOfDays().stream().filter(p-> periodOfDays.contains(p.getId())).collect(Collectors.toList());


        if(filteredPeriods.size()==0 || filteredPeriods.size()==PeriodOfDays.getAllPeriodOfDays().size()){
            return null;
        }

        String cName = getColumnName(columnsMapping,field);

        StringJoiner orJoiner = new StringJoiner(" OR ");
        filteredPeriods.forEach(fp->{
              orJoiner.add("("+cName+" BETWEEN '"+fp.getStartTime()+"' AND '"+fp.getEndTime()+"')");
        });




        //  String pName = getParameterName(field);
        return new WhereBuilderResult("("+ orJoiner +")",null);
    }




}
