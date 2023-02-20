package com.mgo.service;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class GroupByTotalCount {
    private Long totalCount;
    private String id;

    public GroupByTotalCount() {
    }


    public GroupByTotalCount(Long totalCount, String organizationId) {
        this.totalCount = totalCount;
        this.id = organizationId;
    }


    public GroupByTotalCount(String organizationId, Long totalCount) {
        this.totalCount = totalCount;
        this.id = organizationId;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
