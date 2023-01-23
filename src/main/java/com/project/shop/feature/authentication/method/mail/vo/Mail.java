package com.project.shop.feature.authentication.method.mail.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

@Data
public class Mail implements Serializable {
    private static final long serialVersionUID = -398475198749187239L;
    private String from;
    private String to;
    private String subject;
    private String text;
    private String templateName;
    Map<String, Object> variables;
}
