package com.project.shop.feature.manage.product.dto;

import com.project.shop.feature.manage.product.entity.Product;
import lombok.Data;

@Data
public class PostDetailUpdate {
    private int productID;
    private String name;
    private int fullQuantity;
    private String info;

    public Product toEntity(int soldQuantity, int leftQuantity, String thumbnailImageName) {
        Product product = new Product();
        product.setProductID(this.productID);
        product.setName(this.name);
        product.setFullQuantity(this.fullQuantity);
        product.setSoldQuantity(soldQuantity);
        product.setLeftQuantity(leftQuantity);
        product.setInfo(this.info);
        product.setThumbnailImageName(thumbnailImageName);
        return product;
    }
}
