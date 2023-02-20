package com.mgo.querybuilder;
import com.mgo.model.common.OffsetPaging;
import com.mgo.util.Strings;
import io.quarkus.panache.common.Parameters;
import io.quarkus.panache.common.Sort;

import java.util.Map;

public class BuildQueryResult {
   private String query;
   private Map<String, Object> parameters;
   private String sorting;
   private OffsetPaging paging;

    private  String from;

    private String alias;


    public BuildQueryResult() {
    }


    public String getSelectQuery(){
        StringBuilder builder = new StringBuilder();

        builder.append("FROM ").append(from).append(" ").append(alias);


        if(!Strings.isEmptyOrNull(query)){

            builder.append(" WHERE ").append(query);
        }


        if(!Strings.isEmptyOrNull(sorting)){
            builder.append(" order by ").append(sorting);
        }



        return builder.toString();
    }


    public String getCountQuery(){
        StringBuilder builder = new StringBuilder();
        builder.append("FROM ").append(from).append(" ").append(alias);
        if(!Strings.isEmptyOrNull(query)){
            builder.append(" WHERE ").append(query);
        }

        return builder.toString();

    }

    /*    String query = "FROM "+from+" "+alias;
        query = query + " WHERE "+whereBuilderResult.getQuery();*/



    public String getQuery() {
        if(Strings.isEmptyOrNull(query)){
            return null;
        }
        return query;
    }

    public void setQuery(String query) {

        if(Strings.isEmptyOrNull(query)){
            this.query = query;


        }
        else {
            this.query = query.trim();
        }
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, Object> parameters) {
        this.parameters = parameters;
    }

    public String getSorting() {
        return sorting;
    }

    public void setSorting(String sorting) {
        this.sorting = sorting;
    }

    public OffsetPaging getPaging() {
        return paging;
    }

    public void setPaging(OffsetPaging paging) {
        this.paging = paging;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}
