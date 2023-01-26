package com.project.shop.feature.inquiry.dto;

import lombok.Data;

import java.util.Date;

@Data
public class GetReadResponse {
    private int inquiryID;
    private String title;
    private String content;
    private String writer;
    private Date createDate;
    private Date updateDate;
}
