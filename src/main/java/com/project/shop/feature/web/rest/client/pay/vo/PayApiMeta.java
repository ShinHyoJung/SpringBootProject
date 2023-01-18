package com.project.shop.feature.web.rest.client.pay.vo;


import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class PayApiMeta {
    @Value("${spring.pay.key.apiKey}")
    private String apiKey;
    @Value("${spring.pay.key.apiSecretKey}")
    private String apiSecretKey;
}
