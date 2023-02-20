package com.mgo.query;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

public class OffsetPaging {
    @Schema(name = "offset", description = "The offset")
    long offset;

    @Schema(name = "limit", description = "The limit")
    long limit;
    // Check
}
