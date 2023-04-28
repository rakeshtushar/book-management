package com.vmware.bookmanagement.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeCalculator {

    private static String format = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";
    private static DateFormat dateFormat;

    static{
        dateFormat = new SimpleDateFormat(format);
    }

    public static String getCurrentTime()
    {
       return dateFormat.format(new Date());
    }
}
