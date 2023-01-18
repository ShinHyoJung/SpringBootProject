package com.project.shop.feature.web.rest.client.pay.service.impl;

import com.project.shop.feature.web.rest.client.pay.service.PayService;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * <pre>
 * Created with IntelliJ IDEA.
 * Company: NANDSOFT
 * User: sljh1020
 * Date: 2023-01-18
 * Comments:
 * </pre>
 */
@SpringBootTest
class DefaultPayServiceTest {

    @Autowired
    PayService payService;

    @Test
    void getToken() throws ParseException {
        String payResponse  = payService.getToken();
        System.out.println(payResponse);
    }

    @Test
    void paymentInfo() {

        //int amount = payService.paymentInfo()
    }
}