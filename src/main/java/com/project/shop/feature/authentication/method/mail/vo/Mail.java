package com.project.shop.feature.authentication.method.mail.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

@Data
public class Mail implements Serializable {

    private String from;
    private String to;
    private String subject;
    private String text;
    private String templateName;
    Map<String, Object> variables;
}
