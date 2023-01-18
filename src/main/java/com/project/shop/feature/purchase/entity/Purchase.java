package com.project.shop.feature.purchase.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Purchase {
    private int purchaseID;
    private String name;
    private String price;
    private String address;
    private String thumbnailImageName;
    private String impUid;
    private int productID;
    private int sellID;
    private int idx;
    private String orderStatus;
    private Date purchaseDate;
}
