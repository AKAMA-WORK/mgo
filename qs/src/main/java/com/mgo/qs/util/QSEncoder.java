package com.mgo.qs.util;

import com.mgo.qs.model.Charset;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class QSEncoder {

    public static String encode(String input) {
        try {
            return URLEncoder.encode(input, Charset.UTF8.getCharset());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

}
