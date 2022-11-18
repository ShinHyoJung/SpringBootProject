package com.project.shop.feature.authentication.method.sms.dto;

import lombok.Data;

@Data
public class PostSendSms {
    private String mobile;
    private String type;
}
