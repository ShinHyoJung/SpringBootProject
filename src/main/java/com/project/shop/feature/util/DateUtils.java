package com.project.shop.feature.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    public static Date addDate(Date date, int field, int amount) {
        Calendar addDate = Calendar.getInstance();
        addDate.setTime(date);
        addDate.add(field, amount);
        return addDate.getTime();
    }
}
