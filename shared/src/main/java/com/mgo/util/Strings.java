package com.mgo.util;

public class Strings {

    public static boolean isEmptyOrNull(String str) {
        return str == null || str.trim().isEmpty();
    }

    public static String normalizeMsisdn(String msisdn) {
        if (msisdn == null) {
            return msisdn;
        }

        if (msisdn.startsWith("0")) {
            return msisdn.substring(1);
        }
        // Todo: international msisdn
        return msisdn;

    }
}
