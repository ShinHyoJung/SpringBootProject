package com.project.shop.feature.cart.entity;

import lombok.Data;

/**
 * <pre>
 * Created with IntelliJ IDEA.
 * Company: NANDSOFT
 * User: sljh1020
 * Date: 2023-01-17
 * Comments:
 * </pre>
 */
@Data
public class Cart {
    private int cartID;
    private String name;
    private String price;
    private int quantity;
    private int sellID;
    private String thumbnailImageName;
}
