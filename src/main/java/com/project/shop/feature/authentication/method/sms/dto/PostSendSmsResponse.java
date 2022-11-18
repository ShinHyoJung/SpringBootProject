package com.project.shop.feature.authentication.method.sms.dto;

import lombok.Data;

@Data
public class PostSendSmsResponse {
    private String code;
    private String message;
}
