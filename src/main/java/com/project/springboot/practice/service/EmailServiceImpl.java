package com.project.springboot.practice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service("EmailService")
public class EmailServiceImpl implements EmailService
{
    @Autowired
    JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    String username;

    @Override
    public void sendMail(String mail, String crNum)
    {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(username);
        simpleMailMessage.setTo(mail);
        simpleMailMessage.setSubject("메일발송");
        simpleMailMessage.setText("인증번호: " + crNum);

        javaMailSender.send(simpleMailMessage);
    }
}
