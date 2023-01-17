package com.project.shop.feature.web.rest.client.pay.vo;


import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class PayApiMeta {
    @Value("${spring.pay.apiKey}")
    private String apiKey;
    @Value("${spring.pay.apiSecretKey}")
    private String apiSecretKey;
}
