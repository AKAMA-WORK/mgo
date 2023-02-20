package com.mgo.qs.interfaces;

import com.mgo.qs.model.StringifyOptions;

public interface QSAware {

    String toQString(StringifyOptions options);

    String toQString();

    String toJsonString();
}
