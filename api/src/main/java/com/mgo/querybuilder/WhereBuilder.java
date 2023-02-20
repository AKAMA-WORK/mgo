package com.mgo.querybuilder;

import io.vertx.core.json.Json;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import java.util.*;

public class WhereBuilder {
    private final WhereConditionBuilder DEFAULT_BUILDER = new DefaultWhereConditionBuilder();

    public WhereBuilderResult build(JsonObject filter, List<WhereConditionBuilder> conditionBuilders,
                                    Map<String,String> columnsMapping
            , String from, String alias
                                    ){
         if(filter!=null){

             if(filter.containsKey("and")){
               return buildRelation(filter,"and",conditionBuilders,columnsMapping,from,alias);
             }
             else if(filter.containsKey("or")){
                 return buildRelation(filter,"or",conditionBuilders,columnsMapping,from,alias);
             }
            List<String> fields = new ArrayList<>(filter.fieldNames());

            StringJoiner queryJoiner = new StringJoiner(" and ");
            Map<String,Object> parameters = new HashMap<>();

             for (String field : fields) {
                 WhereConditionBuilder builder = getBuilder(field, conditionBuilders);
                 FieldOpAndValue opAndValue = getFieldOpAndValue(filter, field);
                 if (opAndValue != null) {
                     WhereBuilderResult result = builder.build(this, columnsMapping, opAndValue.getOp(), field, opAndValue.getValue(),from,alias);
                     if (result != null) {
                         if (result.getQuery() != null) {
                             queryJoiner.add(result.getQuery());
                         }

                         if (result.getParameters() != null) {
                             parameters.putAll(result.getParameters());
                         }
                     }
                 }
             }

            return new WhereBuilderResult(queryJoiner.toString(),parameters);
         }

        return null;
    }



    private FieldOpAndValue getFieldOpAndValue (JsonObject filter, String field){

        if(filter.containsKey(field)){


            JsonObject fieldValue = filter.getJsonObject(field);
            if(fieldValue==null){
                return null;
            }

            for(int i=0; i<SqlComparisonBuilder.OPERATORS.size();i++){
                String op = SqlComparisonBuilder.OPERATORS.get(i);
                if(fieldValue.containsKey(op)){
                    Object value = fieldValue.getValue(op);

                    if(value!=null){
                        if(value instanceof JsonArray){
                            Object [] valueArray = new Object[((JsonArray) value).size()];

                            for (int j=0;j<((JsonArray) value).size();j++){
                                valueArray[j]=((JsonArray) value).getValue(j);
                            }

                            return new FieldOpAndValue(op,valueArray);

                        }

                        return new FieldOpAndValue(op,value);
                    }
                }



            }
        }


        return null;
    }
    private WhereConditionBuilder getBuilder (String field, List<WhereConditionBuilder> conditionBuilders){
        if(conditionBuilders==null){
            return DEFAULT_BUILDER;
        }

        return conditionBuilders.stream().filter(conditionBuilder -> conditionBuilder.getSupportedFields()==null || conditionBuilder.getSupportedFields().contains(field)).findFirst().orElse(DEFAULT_BUILDER);



    }

    private WhereBuilderResult buildRelation(JsonObject filter, String op, List<WhereConditionBuilder> conditionBuilder,Map<String,String> columnsMapping, String from, String alias){
        JsonArray list =  filter.getJsonArray(op);
        if(list.size()==0){
            return null;
        }
        if(list.size()==1){
            return build(list.getJsonObject(0),conditionBuilder,columnsMapping,from,alias);
        }

        StringJoiner queryJoiner = new StringJoiner(" "+op+" ");
        Map<String,Object> parameters = new HashMap<>();
        for(int i=0;i<list.size();i++){
            WhereBuilderResult result = build(list.getJsonObject(i),conditionBuilder,columnsMapping,from,alias);
            if(result!=null){
                if(result.getQuery()!=null) queryJoiner.add(result.getQuery());
                if(result.getParameters()!=null)  parameters.putAll(result.getParameters());
            }
        }

        return new WhereBuilderResult(queryJoiner.toString(),parameters);
    }



}
