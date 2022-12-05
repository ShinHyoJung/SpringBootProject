package com.project.shop.feature.sell.dto;

import lombok.Data;

@Data
public class PostUpdate {
    private String title;
    private String content;
    private String productCode;
    private String price;
}
