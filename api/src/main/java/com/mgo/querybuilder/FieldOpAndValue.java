package com.mgo.querybuilder;

import java.util.Arrays;
import java.util.List;

public class FieldOpAndValue {

    private final String op;
    private final Object value;

    public FieldOpAndValue(String op, Object value) {
        this.op = op;
        this.value = value;
    }

    public String getOp() {
        return op;
    }

    public Object getValue() {
        return value;
    }
}
