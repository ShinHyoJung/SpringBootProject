package com.project.shop.feature.sell.dto;

import com.project.shop.feature.imagefile.entity.Image;
import lombok.Data;

@Data
public class GetUpdateResponse {
    private int sellID;
    private String title;
    private String content;
    private String productCode;
    private String price;
    private Image image;
}
