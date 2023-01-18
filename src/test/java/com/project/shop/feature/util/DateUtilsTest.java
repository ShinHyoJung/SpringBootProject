package com.project.shop.feature.util;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <pre>
 * Created with IntelliJ IDEA.
 * Company: NANDSOFT
 * User: sljh1020
 * Date: 2023-01-17
 * Comments:
 * </pre>
 */
@SpringBootTest
class DateUtilsTest {

    @Test
    void addDate() {
        Date resultDate = DateUtils.calculateDate(new Date(), -3);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(resultDate);
        System.out.println(date);
    }

    @Test
    void getDiffDays() throws ParseException {
        String dateStr = "2023-01-17";
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
        long diffDays = DateUtils.getDayDifference(date, new Date());
        System.out.println(diffDays);
    }
}