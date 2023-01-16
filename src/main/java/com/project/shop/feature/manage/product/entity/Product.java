package com.project.shop.feature.manage.product.entity;

import lombok.Data;

import java.util.Date;

/**
 * <pre>
 * Created with IntelliJ IDEA.
 * Company: NANDSOFT
 * User: sljh1020
 * Date: 2023-01-04
 * Comments:
 * </pre>
 */
@Data
public class Product {
    private int productID;
    private String code;
    private String name;
    private int fullQuantity;
    private int soldQuantity;
    private int leftQuantity;
    private String info;
    private String category;
    private String thumbnailImageName;
    private String detailImageName;
    private Date registerDate;
    private Date updateDate;
}
