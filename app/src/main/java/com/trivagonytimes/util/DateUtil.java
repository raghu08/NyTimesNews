package com.trivagonytimes.util;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;



public class DateUtil {

    public static final String FORMAT1 = "yyyy-MM-dd'T'HH:mm:ss+0000";
    public static final String FORMAT2 = "yyyy-MM-dd";

    public static String parseDate(String dateStr,String format) {
        SimpleDateFormat inputFormat = new SimpleDateFormat(format, Locale.ENGLISH);
        String formattedDate = "";
        try {
            Date date = inputFormat.parse(dateStr);
            Format outputFormat = new SimpleDateFormat("MMMM dd yyyy",Locale.ENGLISH);
            formattedDate = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formattedDate;
    }
}
