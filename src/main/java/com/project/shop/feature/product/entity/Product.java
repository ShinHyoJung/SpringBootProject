package com.project.shop.feature.product.entity;

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
    private String thumbnailImageName;
    private Date registerDate;
    private Date updateDate;
}
