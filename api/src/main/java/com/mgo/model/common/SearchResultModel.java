package com.mgo.model.common;


import java.util.List;

public class SearchResultModel<T> {
   List<T> items;
   long totalCount;

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }
}
