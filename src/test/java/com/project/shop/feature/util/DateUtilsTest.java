package com.project.shop.feature.util;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

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
        Date resultDate = DateUtils.addDate(new Date(), 3, 3);
        System.out.println();
    }
}