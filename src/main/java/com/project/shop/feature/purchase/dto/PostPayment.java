package com.project.shop.feature.purchase.dto;

import lombok.Data;

@Data
public class PostPayment {
    private int sellID;
    private String name;
    private String price;
    private String quantity;
}
