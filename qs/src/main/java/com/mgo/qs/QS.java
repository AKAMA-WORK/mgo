package com.mgo.qs;

import com.mgo.qs.model.ParseOptions;
import com.mgo.qs.model.StringifyOptions;
import com.mgo.qs.model.QSObject;
import com.mgo.qs.parser.ParseException;
import com.mgo.qs.parser.QSParser;
import com.mgo.qs.stringify.Stringifier;

import java.io.IOException;
import java.io.Reader;

public class QS {

    private QS() {
    }

    public static QSObject parse(String s) throws ParseException {
        QSParser parser = new QSParser();
        return parser.parse(s);
    }

    public static QSObject parse(String s, ParseOptions options) throws ParseException {
        QSParser parser = new QSParser();
        return parser.parse(s, options);
    }

    public static QSObject parse(Reader in, ParseOptions options) throws IOException, ParseException {
        QSParser parser = new QSParser();
        return parser.parse(in, options);
    }

    public static String toQString(QSObject object) {
        return Stringifier.toQString(object);
    }

    public static String toQString(QSObject object, StringifyOptions options) {
        return Stringifier.toQString(object, options);
    }

    public static String toJsonString(QSObject object) {
        return Stringifier.toJsonString(object);
    }
}
