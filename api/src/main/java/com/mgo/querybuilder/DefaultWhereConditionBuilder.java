package com.mgo.querybuilder;


import io.vertx.core.json.JsonObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class DefaultWhereConditionBuilder implements WhereConditionBuilder {
    @Override
    public List<String> getSupportedFields() {
        return null;
    }


    protected String getColumnName (Map<String,String> columnsMapping, String field){

        return  QueryBuilderUtil.getColumnName(columnsMapping,field);
    }

    protected  Object getParameterValue(Object value){
        return  value;
    }

    protected String getParameterName (String field) {
        return "p"+field;
    }
    @Override
    public WhereBuilderResult build(WhereBuilder builder, Map<String,String> columnsMapping, String op, String field, Object value, String from, String alias) {
        if(SqlComparisonBuilder.DEFAULT_COMPARISON_MAP.containsKey(op)){
            return this.buildComparison(builder,columnsMapping,op,field,value,from,alias);
        }

        if("is".equals(op)){
            return this.buildIs(builder,columnsMapping,op,field,value,from,alias);
        }

        if("isNull".equals(op)){
            return this.buildIsNull(builder,columnsMapping,op,field,value,from,alias);
        }

        if("between".equals(op)){
            return this.buildBetween(builder,columnsMapping,op,field,value,from,alias);
        }


        return null;
    }

    protected WhereBuilderResult buildComparison(WhereBuilder builder, Map<String,String> columnsMapping, String op, String field, Object value, String from, String alias){
        String pName = getParameterName(field);
        String cName = getColumnName(columnsMapping,field);

        if(op.equals("in") || op.equals("nin")){
            return new WhereBuilderResult(cName+" "+SqlComparisonBuilder.DEFAULT_COMPARISON_MAP.get(op)+" ("+this.buildListValue((Object[]) getParameterValue(value),true)+")" ,null);
        }

        return new WhereBuilderResult(cName+" "+SqlComparisonBuilder.DEFAULT_COMPARISON_MAP.get(op)+" :"+pName ,new HashMap<>(){{
            put(pName,getParameterValue(value));
        }});
    }

    protected String buildListValue(
           Object [] value,
           boolean quoted
    ){
        if(value==null || value.length==0){
            return null;
        }


        StringJoiner joiner = new StringJoiner(",");

        for (Object item: value){

            if(quoted){
                joiner.add("'"+item.toString()+"'");
            }
            else {
                joiner.add(item.toString());
            }

        }

        return joiner.toString();

    }

        protected WhereBuilderResult buildIs(WhereBuilder builder, Map<String,String> columnsMapping, String op, String field, Object value, String from, String alias){
        if(value instanceof Boolean){
            String cName = getColumnName(columnsMapping,field);
            boolean valueAsBoolean = (Boolean) value;
            return new WhereBuilderResult( valueAsBoolean? cName+" IS TRUE":cName+" IS FALSE" ,null);
        }

        return null;
    }

    protected WhereBuilderResult buildIsNull(WhereBuilder builder, Map<String,String> columnsMapping, String op, String field, Object value, String from, String alias){
        if(value instanceof Boolean){
            String cName = getColumnName(columnsMapping,field);

            boolean valueAsBoolean = (Boolean) value;
            return new WhereBuilderResult( valueAsBoolean? cName+" IS NULL":cName+" IS NOT NULL" ,null);
        }

        return null;
    }


        protected WhereBuilderResult buildBetween(WhereBuilder builder, Map<String,String> columnsMapping, String op, String field, Object value, String from, String alias){
        JsonObject minMax = (JsonObject) value;
        String cName = getColumnName(columnsMapping,field);
        String pName = getParameterName(field);

        Object lower = minMax.getValue("lower");
        Object upper = minMax.getValue("upper");

        if(lower!=null && upper!=null){
            return new WhereBuilderResult( cName+" BETWEEN :pMin"+pName+" AND :pMax"+pName,new HashMap<>(){{
                put("pMin"+pName,getParameterValue(lower));
                put("pMax"+pName,getParameterValue(upper));
            }});
        }

        return null;
    }
}
