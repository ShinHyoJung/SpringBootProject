package com.project.shop.feature.authentication.method.mail.dto;

import lombok.Data;

@Data
public class PostSendMailResponse {
    private String code;
    private String message;
}
