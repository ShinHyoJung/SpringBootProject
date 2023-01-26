package com.project.shop.feature.inquiry.dto;

import lombok.Data;

@Data
public class GetUpdateResponse {
    private int inquiryID;
    private String title;
    private String writer;
    private String content;
}
