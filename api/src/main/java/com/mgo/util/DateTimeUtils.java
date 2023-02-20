package com.mgo.util;

import java.time.Instant;
import java.util.Date;

public class DateTimeUtils {
   public static long secondsBetween(Date d1,Date d2){
        return  (d2.getTime() - d1.getTime())/1000;
    }

    public static boolean isTimeElapsed(Date date, Date elapsedAt){
       if(date!=null){
            return elapsedAt.toInstant().isAfter(date.toInstant());
       }

        return false;
   }
}
