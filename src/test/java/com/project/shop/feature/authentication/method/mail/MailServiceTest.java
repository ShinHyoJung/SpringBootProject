package com.project.shop.feature.authentication.method.mail;

import com.project.shop.feature.authentication.method.mail.service.MailService;
import com.project.shop.feature.authentication.method.mail.vo.Mail;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.mail.MessagingException;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class MailServiceTest {

    @Autowired
    private MailService mailService;

    @Test
    void sendMessage() throws MessagingException {
        Mail mail = new Mail();
        mail.setTo("shj971020@naver.com");
        mail.setSubject("이메일 발송");
        mail.setTemplateName("mail");
        mail.setText("sdfs");
        Map<String, Object> variable = new HashMap<>();
        variable.put("text", "안녕하세요");
        mail.setVariables(variable);

        mailService.send(mail);
    }

}
