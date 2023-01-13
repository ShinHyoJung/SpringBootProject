package com.project.shop.feature.sell.dto;

import com.project.shop.feature.sell.entity.Sell;
import lombok.Data;

@Data
public class PostRegister {
    private String name;
    private String title;
    private String content;
    private String price;

    public Sell toEntity(String thumbnailImageName) {
        Sell sell = new Sell();
        sell.setName(this.name);
        sell.setTitle(this.title);
        sell.setContent(this.content);
        sell.setThumbnailImageName(thumbnailImageName);
        sell.setPrice(this.price);
        return sell;
    }
}
