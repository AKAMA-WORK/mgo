package com.mgo.qs.interfaces;

import com.mgo.qs.model.StringifyOptions;

import java.io.IOException;
import java.io.Writer;

public interface QStreamAware {
    void writeQString(Writer out) throws IOException;

    void writeQString(Writer out, StringifyOptions options) throws IOException;
}
