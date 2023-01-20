package com.project.shop.feature.authentication.method.email.service;

import com.project.shop.feature.authentication.method.email.vo.Mail;

import javax.mail.MessagingException;

public interface MailService {

    void send(Mail mail) throws MessagingException;
}
