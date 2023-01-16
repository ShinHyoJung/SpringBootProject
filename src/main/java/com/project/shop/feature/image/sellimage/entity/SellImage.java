package com.project.shop.feature.image.sellimage.entity;

import lombok.Data;

import java.util.Date;

@Data
public class SellImage {
    private int imageID;
    private String orgName;
    private String storedName;
    private String size;
    private int sellID;
    private String path;
    private Date createDate;
    private String deleteYN;
}
