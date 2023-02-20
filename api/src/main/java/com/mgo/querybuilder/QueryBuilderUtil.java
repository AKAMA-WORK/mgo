package com.mgo.querybuilder;

import java.util.Map;

public class QueryBuilderUtil {
    public static String getColumnName (Map<String,String> columnsMapping, String field){
        if(columnsMapping!=null && columnsMapping.containsKey(field)){
            return columnsMapping.get(field);
        }
        return field;
    }
}
