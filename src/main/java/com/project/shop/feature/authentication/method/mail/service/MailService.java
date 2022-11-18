package com.project.shop.feature.authentication.method.mail.service;

import com.project.shop.feature.authentication.method.mail.vo.Mail;

import javax.mail.MessagingException;

public interface MailService {

    void send(Mail mail) throws MessagingException;
}
