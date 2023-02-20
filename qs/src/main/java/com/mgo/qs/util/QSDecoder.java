package com.mgo.qs.util;

import com.mgo.qs.model.Charset;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class QSDecoder {

    public static String decode(String input) {
        try {
            if (input == null) return null;
            return URLDecoder.decode(input, Charset.UTF8.getCharset());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }
}
