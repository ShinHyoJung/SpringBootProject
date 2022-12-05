package com.project.shop.feature.sell.dto;

import lombok.Data;

import java.util.Date;

@Data
public class GetDetailResponse {
    private int sellID;
    private String title;
    private String content;
    private String price;
    private String detailImage;
    private Date createDate;
    private Date updateDate;
}
