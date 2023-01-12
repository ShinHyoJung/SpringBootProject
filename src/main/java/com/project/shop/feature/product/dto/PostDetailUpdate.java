package com.project.shop.feature.product.dto;

import com.project.shop.feature.product.entity.Product;
import lombok.Data;

@Data
public class PostDetailUpdate {
    private int productID;
    private String name;
    private int fullQuantity;
    private String info;

    public Product toEntity() {
        Product product = new Product();
        product.setProductID(this.productID);
        product.setName(this.name);
        product.setFullQuantity(this.fullQuantity);
        product.setInfo(this.info);
        return product;
    }
}
