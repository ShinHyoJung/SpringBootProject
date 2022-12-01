package com.project.shop.feature.imagefile.entity;

import lombok.Data;

import java.util.Date;

@Data
public class ImageFile {
    private int imageFileID;
    private String orgName;
    private String storedName;
    private String size;
    private int productID;
    private String productCode;
    private int sellID;
    private String path;
    private Date createDate;
    private String deleteYN;

}
