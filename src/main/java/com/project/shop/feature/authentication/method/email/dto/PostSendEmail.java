package com.project.shop.feature.authentication.method.email.dto;

import lombok.Data;

@Data
public class PostSendEmail {
    private String email;
    private String subject;
    private String templateName;
    private String text;
}
