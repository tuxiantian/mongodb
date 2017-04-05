package com.laijia.mongodb.util;

import com.google.gson.Gson;

/**
 * json������ת��������
 *
 * @author wbq
 */
public class JsonUtil {

    public static String toJson(Object o) {
        String ret = null;
        if (o == null) {
            ret = "{}";
        } else {
            ret = new Gson().toJson(o);
        }
        return ret;
    }

    public static <T> T toObject(String val, Class<T> clazz) {
        if (val != null && val.length() > 0) {
            return new Gson().fromJson(val, clazz);
        }
        return (T) null;
    }
}
