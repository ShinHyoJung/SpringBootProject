package com.project.shop.feature.image.productimage.entity;

import lombok.Data;

import java.util.Date;

@Data
public class ProductImage {
    private int imageID;
    private String orgName;
    private String storedName;
    private String size;
    private int productID;
    private String path;
    private Date createDate;
    private String deleteYN;
}
