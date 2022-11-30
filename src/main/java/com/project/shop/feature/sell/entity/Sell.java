package com.project.shop.feature.sell.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Sell {
    private int sellID;
    private int productID;
    private String code;
    private String name;
    private String sellImageOrg;
    private String sellImage;
    private Date createDate;
    private Date updateDate;
}
