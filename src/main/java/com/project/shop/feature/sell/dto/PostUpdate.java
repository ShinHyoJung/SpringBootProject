package com.project.shop.feature.sell.dto;

import com.project.shop.feature.sell.entity.Sell;
import lombok.Data;

@Data
public class PostUpdate {
    private int sellID;
    private String title;
    private String name;
    private String content;
    private String price;

    public Sell toEntity(String thumbnailImageName, String titleImageName, String detailImageName) {
        Sell sell = new Sell();
        sell.setSellID(this.sellID);
        sell.setTitle(this.title);
        sell.setName(this.name);
        sell.setContent(this.content);
        sell.setPrice(this.price);
        sell.setThumbnailImageName(thumbnailImageName);
        sell.setTitleImageName(titleImageName);
        sell.setDetailImageName(detailImageName);
        return sell;
    }
}
