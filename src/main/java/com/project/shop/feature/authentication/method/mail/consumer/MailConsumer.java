package com.project.shop.feature.authentication.method.mail.consumer;

import com.project.shop.feature.authentication.method.mail.service.MailService;
import com.project.shop.feature.authentication.method.mail.vo.Mail;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;

@Component
@RequiredArgsConstructor
public class MailConsumer {
    private final MailService mailService;

    @JmsListener(destination = "sendSimpleMail")
    public void sendSimpleMail(Mail mail) throws MessagingException {
        mailService.send(mail);
    }

}
