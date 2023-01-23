package com.project.shop.feature.authentication.method.mail.publisher.service.impl;

import com.project.shop.feature.authentication.method.mail.publisher.service.MailPublisherService;
import com.project.shop.feature.authentication.method.mail.vo.Mail;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service("MailPublisherService")
public class DefaultMailPublisherService implements MailPublisherService {

    private final JmsTemplate jmsTemplate;


    @Override
    public void sendSimpleMail(Mail mail) {
        jmsTemplate.convertAndSend("sendSimpleMail", mail);
    }
}
