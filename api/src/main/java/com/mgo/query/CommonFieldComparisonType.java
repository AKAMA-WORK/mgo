package com.mgo.query;

public class CommonFieldComparisonType<FieldType>  extends BooleanFieldComparisons {
    FieldType eq;
    FieldType neq;
    FieldType gt;
    FieldType gte;
    FieldType lt;
    FieldType lte;
    FieldType [] in;
    FieldType [] notIn;
    CommonFieldComparisonBetweenType<FieldType> between;
    CommonFieldComparisonBetweenType<FieldType> notBetween;


    CommonFieldComparisonType<FieldType>[] and;
    CommonFieldComparisonType<FieldType>[] or;

    public FieldType getEq() {
        return eq;
    }

    public void setEq(FieldType eq) {
        this.eq = eq;
    }

    public FieldType getNeq() {
        return neq;
    }

    public void setNeq(FieldType neq) {
        this.neq = neq;
    }

    public FieldType getGt() {
        return gt;
    }

    public void setGt(FieldType gt) {
        this.gt = gt;
    }

    public FieldType getGte() {
        return gte;
    }

    public void setGte(FieldType gte) {
        this.gte = gte;
    }

    public FieldType getLt() {
        return lt;
    }

    public void setLt(FieldType lt) {
        this.lt = lt;
    }

    public FieldType getLte() {
        return lte;
    }

    public void setLte(FieldType lte) {
        this.lte = lte;
    }

    public FieldType[] getIn() {
        return in;
    }

    public void setIn(FieldType[] in) {
        this.in = in;
    }

    public FieldType[] getNotIn() {
        return notIn;
    }

    public void setNotIn(FieldType[] notIn) {
        this.notIn = notIn;
    }

    public CommonFieldComparisonBetweenType<FieldType> getBetween() {
        return between;
    }

    public void setBetween(CommonFieldComparisonBetweenType<FieldType> between) {
        this.between = between;
    }

    public CommonFieldComparisonBetweenType<FieldType> getNotBetween() {
        return notBetween;
    }

    public void setNotBetween(CommonFieldComparisonBetweenType<FieldType> notBetween) {
        this.notBetween = notBetween;
    }

    public CommonFieldComparisonType<FieldType>[] getAnd() {
        return and;
    }

    public void setAnd(CommonFieldComparisonType<FieldType>[] and) {
        this.and = and;
    }

    public CommonFieldComparisonType<FieldType>[] getOr() {
        return or;
    }

    public void setOr(CommonFieldComparisonType<FieldType>[] or) {
        this.or = or;
    }
}
