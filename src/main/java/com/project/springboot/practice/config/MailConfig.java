package com.project.springboot.practice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfig {
    @Value(("${spring.mail.host}"))
    String host;

    @Value("${spring.mail.port}")
    int port;

    @Value("${spring.mail.password}")
    String password;

    @Value(("${spring.mail.username}"))
    String username;

    Properties pt = new Properties();

    @Bean
    public JavaMailSender javaMailSender() {

        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(host);
        javaMailSender.setPort(port);
        javaMailSender.setUsername(username);
        javaMailSender.setPassword(password);

        pt.put("mail.smtp.auth", true);
        pt.put("mail.smtp.starttls.enable", true);
        javaMailSender.setJavaMailProperties(pt);

        return javaMailSender;
    }

}
