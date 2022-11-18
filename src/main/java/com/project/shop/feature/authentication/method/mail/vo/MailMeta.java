package com.project.shop.feature.authentication.method.mail.vo;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class MailMeta {

    @Value(("${spring.mail.username}"))
    private String from;
}
