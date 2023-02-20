package com.mgo.util;

import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;

public class Strings {
    private static final PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();

    public static boolean isEmptyOrNull(String str) {
        return str == null || str.trim().isEmpty();
    }

    public static String normalizeMsisdn(String msisdn) {
        if (msisdn == null) {
            return null;
        }

        try {
            Phonenumber.PhoneNumber phoneNumberProto = phoneUtil.parse(msisdn, "MG");
            return phoneUtil.format(phoneNumberProto, PhoneNumberUtil.PhoneNumberFormat.E164);
        }
        catch (Exception ex){
              return null;
        }
    }
}
