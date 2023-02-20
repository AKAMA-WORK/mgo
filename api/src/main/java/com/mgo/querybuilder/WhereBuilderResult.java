package com.mgo.querybuilder;

import java.util.Map;

public class WhereBuilderResult {
    private final Map<String,Object> parameters;
    private final String query;

    public WhereBuilderResult(String query,Map<String, Object> parameters) {
        this.parameters = parameters;
        this.query = query;
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }

    public String getQuery() {
        return query;
    }


}
