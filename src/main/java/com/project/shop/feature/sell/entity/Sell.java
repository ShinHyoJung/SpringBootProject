package com.project.shop.feature.sell.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Sell {
    private int sellID;
    private String name;
    private String title;
    private String content;
    private String price;
    private String thumbnailImage;
    private String thumbnailImagePath;
    private String detailImage;
    private String detailImagePath;
    private Date createDate;
    private Date updateDate;
    private int productID;
    private String productCode;
}
