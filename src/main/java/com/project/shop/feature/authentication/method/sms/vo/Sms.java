package com.project.shop.feature.authentication.method.sms.vo;

import lombok.Data;

@Data
public class Sms {
    private String to;
    private String type;
    private String text;
}
