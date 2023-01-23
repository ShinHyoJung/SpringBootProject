package com.project.shop.feature.want.entity;

import lombok.Data;

@Data
public class Want {
    private int wantID;
    private String name;
    private String price;
    private String thumbnailImageName;
    private int sellID;
    private int idx;
}
