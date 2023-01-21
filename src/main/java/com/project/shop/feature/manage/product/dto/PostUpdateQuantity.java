package com.project.shop.feature.manage.product.dto;

import com.project.shop.feature.manage.product.entity.Product;
import lombok.Data;

@Data
public class PostUpdateQuantity {

    public Product toEntity(int productID, int quantity) {
        Product product = new Product();
        product.setProductID(productID);
        product.setSoldQuantity(quantity);
        product.setLeftQuantity(product.getFullQuantity() - quantity);
        return product;
    }
}
