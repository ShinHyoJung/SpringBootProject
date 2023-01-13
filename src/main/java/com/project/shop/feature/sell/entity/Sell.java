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
    private String thumbnailImageName;
    private String detailImageName;
    private Date createDate;
    private Date updateDate;
    private int productID;
}
