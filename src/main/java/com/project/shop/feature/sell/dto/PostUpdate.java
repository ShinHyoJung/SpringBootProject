package com.project.shop.feature.sell.dto;

import com.project.shop.feature.sell.entity.Sell;
import lombok.Data;

@Data
public class PostUpdate {
    private int sellID;
    private String title;
    private String content;
    private String price;

    public Sell toEntity() {
        Sell sell = new Sell();
        sell.setSellID(this.sellID);
        sell.setTitle(this.title);
        sell.setContent(this.content);
        sell.setPrice(this.price);
        return sell;
    }
}
