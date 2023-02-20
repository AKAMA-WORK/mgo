package com.mgo.query;

public class CommonFieldComparisonBetweenType<FieldType> {
    FieldType lower;
    FieldType upper;

    public FieldType getLower() {
        return lower;
    }

    public void setLower(FieldType lower) {
        this.lower = lower;
    }

    public FieldType getUpper() {
        return upper;
    }

    public void setUpper(FieldType upper) {
        this.upper = upper;
    }
}
