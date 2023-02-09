package com.project.shop.feature.inquiry.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Inquiry {
    private int inquiryID;
    private String loginID;
    private String title;
    private String content;
    private String writer;
    private Date createDate;
    private Date updateDate;
    private int idx;
    private boolean isAnswer;
}
