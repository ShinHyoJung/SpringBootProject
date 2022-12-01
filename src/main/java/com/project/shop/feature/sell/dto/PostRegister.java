package com.project.shop.feature.sell.dto;

import com.project.shop.feature.sell.entity.Sell;
import lombok.Data;

@Data
public class PostRegister {
    private String name;
    private String title;
    private String content;
    private String price;
    private String productCode;
    private String detailImage;
    private String detailImagePath;
    private String thumbnailImage;
    private String thumbnailImagePath;

    public Sell toEntity() {
        Sell sell = new Sell();
        sell.setName(this.name);
        sell.setTitle(this.title);
        sell.setContent(this.content);
        sell.setPrice(this.price);
        sell.setProductCode(this.productCode);
        sell.setDetailImage(this.detailImage);
        sell.setDetailImagePath(this.detailImagePath);
        sell.setThumbnailImage(this.thumbnailImage);
        sell.setThumbnailImagePath(this.thumbnailImagePath);
        return sell;
    }
}
