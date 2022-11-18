package com.project.shop.feature.authentication.method.sms.vo;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class SmsMeta {
    @Value("${spring.sms.from}")
    private String from;
    @Value("${spring.sms.apiKey}")
    private String apiKey;
    @Value("${spring.sms.apiSecret}")
    private String apiSecret;
}
