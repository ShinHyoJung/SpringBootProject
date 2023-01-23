package com.project.shop.feature.authentication.method.mail.publisher.service;

import com.project.shop.feature.authentication.method.mail.vo.Mail;

public interface MailPublisherService {

    void sendSimpleMail(Mail mail);
}
