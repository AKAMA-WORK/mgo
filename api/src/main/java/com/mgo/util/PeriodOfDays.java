package com.mgo.util;

import com.mgo.model.common.PeriodOfDayModel;

import java.util.Arrays;
import java.util.List;

public class PeriodOfDays {

    private static final List<PeriodOfDayModel> list = Arrays.asList(
            new PeriodOfDayModel("morning","Matin","00:00","11:59"),
                new PeriodOfDayModel("afternoon","Après-midi","12:00","17:59"),
                new PeriodOfDayModel("evening","Soir","18:00","23:59")

                );

    public static List<PeriodOfDayModel> getAllPeriodOfDays(){


        return list ;

    }
}
