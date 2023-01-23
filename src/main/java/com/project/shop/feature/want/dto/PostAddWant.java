package com.project.shop.feature.want.dto;

import com.project.shop.feature.want.entity.Want;
import lombok.Data;

@Data
public class PostAddWant {
    private String name;
    private String price;
    private String thumbnailImageName;
    private int sellID;

    public Want toEntity(int idx, String thumbnailImageName) {
        Want want = new Want();
        want.setName(this.name);
        want.setPrice(this.price);
        want.setThumbnailImageName(thumbnailImageName);
        want.setSellID(this.sellID);
        want.setIdx(idx);
        return want;
    }
}
