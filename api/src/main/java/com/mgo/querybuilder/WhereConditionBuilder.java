package com.mgo.querybuilder;

import io.quarkus.panache.common.Parameters;

import java.util.List;
import java.util.Map;

public interface WhereConditionBuilder {
    public List<String> getSupportedFields();
    public WhereBuilderResult build(WhereBuilder builder, Map<String,String> columnsMapping, String op, String field, Object value, String from, String alias);
}
