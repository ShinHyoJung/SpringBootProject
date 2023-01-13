package com.project.shop.feature.image.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Image {
    private int imageID;
    private String orgName;
    private String storedName;
    private String size;
    private String thumbnailImageName;
    private String detailImageName;
    private int productID;
    private int sellID;
    private String path;
    private int type;
    private Date createDate;
    private String deleteYN;
}
