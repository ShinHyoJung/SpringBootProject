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
    private String category;
    private String thumbnailImageName;
    private String titleImageName;
    private String detailImageName;
    private int productID;
    private Date createDate;
    private Date updateDate;
}
