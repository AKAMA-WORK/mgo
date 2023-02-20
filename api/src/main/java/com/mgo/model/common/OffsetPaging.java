package com.mgo.model.common;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter(onMethod = {})
@Data
@Builder(toBuilder = true)
@Schema(name = "OffsetPaging")
public class OffsetPaging {

    @Schema(name = "offset", description = "The offset")
    int offset;

    @Schema(name = "limit", description = "The limit")
    int limit;


    public static OffsetPaging fromString(String str){
        return  null;
    }


    public OffsetPaging() {
    }

    public OffsetPaging(int offset, int limit) {
        this.offset = offset;
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
