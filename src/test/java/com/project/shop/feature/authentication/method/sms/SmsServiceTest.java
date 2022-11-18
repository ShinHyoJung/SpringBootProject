package com.project.shop.feature.authentication.method.sms;

import com.project.shop.feature.authentication.method.sms.service.SmsService;
import com.project.shop.feature.authentication.method.sms.vo.Sms;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SmsServiceTest {

    @Autowired
    SmsService smsService;

    @Test
    void send() throws CoolsmsException {
        Sms sms = new Sms();
        sms.setTo("01052386416");
        sms.setType("SMS");
        sms.setText("안녕하세요");

        smsService.send(sms);
    }
}
