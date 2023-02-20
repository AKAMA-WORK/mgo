package com.mgo.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTimeFormat {
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat HOUR_FORMAT = new SimpleDateFormat("HH:mm");

    public static final SimpleDateFormat DATE_HOUR_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");

   public static final SimpleDateFormat DATETIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

   public static final SimpleDateFormat DATETIME_LONG_FORMAT = new SimpleDateFormat("dd MMM yyyy à HH:mm");


   public static  final  SimpleDateFormat FR_DATE_FORMAT = new SimpleDateFormat("dd/MM/YYYY");
    public static  final  SimpleDateFormat FR_DATETIME_LONG_FORMAT = new SimpleDateFormat("EEE dd MMMM YYYY");

   public static String formatDate (Date date){
       return  date!=null ? DATE_FORMAT.format(date) : null;
   }

    public static String formatHour (Date date){
        return  date!=null ? HOUR_FORMAT.format(date) : null;
    }

    public static String formatDateHour (Date date){
        return  date!=null ? DATE_HOUR_FORMAT.format(date) : null;
    }

    public static String formatDatetime (Date date){
        return  date!=null ? DATETIME_FORMAT.format(date) : null;
    }


    public static String formatFrDate (Date date){
        return  date!=null ? FR_DATE_FORMAT.format(date) : null;
    }

    public static String formatFrDatetimeLong (Date date){
        return  date!=null ? FR_DATETIME_LONG_FORMAT.format(date) : null;
    }


    public static Date parseDate (String date) throws ParseException {
        return  date!=null ? DATE_FORMAT.parse(date) : null;
    }


    public static Date parseDatetime (String date) throws ParseException {
        return  date!=null ? DATETIME_FORMAT.parse(date) : null;
    }


    public static Date parseDatetime(String date, String time) throws ParseException {

       if(date==null){
           return null;
       }
       if(time==null){
           return  parseDatetime(date);
       }

        String [] timeParts = time.split(":");
        Calendar tempStart = Calendar.getInstance();
        tempStart.setTime(DATE_FORMAT.parse(date));
        tempStart.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeParts[0]));
        tempStart.set(Calendar.MINUTE, Integer.parseInt(timeParts[1]));

        return tempStart.getTime();

    }


}


