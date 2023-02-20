package com.mgo.querybuilder;

import com.mgo.model.common.OffsetPaging;
import com.mgo.qs.QS;
import com.mgo.qs.model.ParseOptions;
import com.mgo.qs.parser.ParseException;
import com.mgo.util.Strings;
import io.quarkus.panache.common.Parameters;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;

import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.Map;

public class QueryBuilder<F> {
    private final List<WhereConditionBuilder> conditionBuilders;
    private final Map<String,String> columnsMapping;
    private final WhereBuilder whereBuilder = new WhereBuilder();

    private final String from;
    private final String alias;

    public QueryBuilder(List<WhereConditionBuilder> conditionBuilders,Map<String,String> columnsMapping, String from, String alias) {
        this.conditionBuilders = conditionBuilders;
        this.columnsMapping=columnsMapping;
        this.from = from;
        this.alias = alias;
    }

    //

    public  BuildQueryResult build(UriInfo uriInfo,Class<F> type) throws ParseException {

         if(uriInfo==null || uriInfo.getRequestUri()==null || uriInfo.getRequestUri().getQuery()==null){
             return   build(new JsonObject());
         }

        Map<String,Object> queryParams = QS.parse(uriInfo.getRequestUri().getQuery(), new ParseOptions.Builder().setParseArrays(true).setComma(true).build());



        if(queryParams.containsKey("filter")){
            JsonObject asJson = new JsonObject(queryParams);
            asJson.put("filter",JsonObject.mapFrom(asJson.getJsonObject("filter").mapTo(type)));

            return build(asJson);

        }

        return build(new JsonObject(queryParams));

    }

    // sort / filter
    public  BuildQueryResult build(JsonObject params){


        BuildQueryResult result = new BuildQueryResult();
        result.setFrom(this.from);
        result.setAlias(this.alias);



        String paging = "";



         if(params.containsKey("sort")) {
             String sortQuery = buildSort( params.getJsonObject("sort"));
             if(!Strings.isEmptyOrNull(sortQuery)){
                 result.setSorting(sortQuery);
             }
         }

         if(params.containsKey("paging")){
             OffsetPaging pagingParams = params.getJsonObject("paging").mapTo(OffsetPaging.class);
             result.setPaging(pagingParams);
         }
         else {
             result.setPaging(new OffsetPaging(0,5));
         }


        if(params.containsKey("filter")){
             WhereBuilderResult whereBuilderResult = buildFilter(params.getJsonObject("filter"));
             if(whereBuilderResult!=null){
                 result.setParameters(whereBuilderResult.getParameters());
                 result.setQuery(whereBuilderResult.getQuery());
             }
         }



        return result;
    }

    private WhereBuilderResult buildFilter(
            JsonObject filter

    ){
        if(filter==null){
            return null;
        }
        return whereBuilder.build(filter,conditionBuilders,columnsMapping,this.from,this.alias);

    }




    private String buildSort(
            JsonObject sort
    ){
        if(sort.isEmpty()){
            return null;
        }
       return  sort.stream().map(entry->{
            // TODO : Customized Sort
            return  QueryBuilderUtil.getColumnName(columnsMapping,entry.getKey())+" "+entry.getValue();
        }).reduce("",(prev,current)-> prev.isEmpty()? current :prev+","+current);
    }

}
