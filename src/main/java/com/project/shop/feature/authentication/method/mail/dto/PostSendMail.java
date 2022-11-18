package com.project.shop.feature.authentication.method.mail.dto;

import lombok.Data;

@Data
public class PostSendMail {
    private String mail;
    private String subject;
    private String templateName;
    private String text;
}
