package com.mgo.querybuilder;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SqlComparisonBuilder {
    public static final List<String> OPERATORS = Arrays.asList("is","isNull","eq","neq","gt","gte","lt","lte","in","notIn","like","notLike","iLike","notILike");

    public static final Map<String,String> DEFAULT_COMPARISON_MAP = new HashMap<>(){
        {
            put("eq","=");
            put("neq","!=");
            put("gt",">");
            put("gte",">=");
            put("lt","<");
            put("lte","<=");
            put("like","LIKE");
            put("iLike","ILIKE");
            put("notLike","NOT LIKE");
            put("notILike","NOT ILIKE");
            put("in","IN");
            put("nin","NOT IN");
        }
    };
}
