package com.project.shop.feature.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    public static Date calculateDate(Date date, int amount) {
        Calendar addDate = Calendar.getInstance();
        addDate.setTime(date);
        addDate.add(Calendar.DATE, amount);
        return addDate.getTime();
    }

    public static long getDayDifference(Date beforeDate, Date nowDate) {
        long diffDays = (nowDate.getTime() - beforeDate.getTime()) /1000 / (24 * 60 * 60);
        return diffDays;
    }
}
