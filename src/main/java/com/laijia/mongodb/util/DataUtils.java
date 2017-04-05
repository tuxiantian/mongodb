package com.laijia.mongodb.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author wbq
 */
public class DataUtils {

    public static final SimpleDateFormat sdf = new SimpleDateFormat(
            "yyyy-MM-dd");
    public static final SimpleDateFormat secFormat = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");
    public static final SimpleDateFormat monthDay = new SimpleDateFormat(
            "MM-dd");

    public static final DecimalFormat numFormat = new DecimalFormat("#,###.##");

    public static Date toDate(Object s, String datePatten) {
        if (s == null)
            return null;

        try {
            if (datePatten != null) {
                return new SimpleDateFormat(datePatten).parse(s.toString()
                        .trim());
            } else {
                return sdf.parse(s.toString().trim());
            }
        } catch (Exception e) {
        }
        return null;
    }

    public static Date toDate(Object s) {
        if (s == null)
            return null;

        try {
            return sdf.parse(s.toString().trim());
        } catch (Exception e) {
        }
        return null;
    }

    public static Integer toInteger(Object s) {
        if (s != null) {
            try {
                return Integer.parseInt(s.toString().trim());
            } catch (Exception e) {
            }
        }

        return null;
    }

    public static Long toLong(Object s) {
        if (s != null) {
            try {
                return Long.parseLong(s.toString().trim());
            } catch (Exception e) {
            }
        }

        return null;
    }

    public static Double toDouble(Object s) {
        if (s != null) {
            try {
                return Double.parseDouble(s.toString().trim());
            } catch (Exception e) {
            }
        }

        return null;
    }
}
