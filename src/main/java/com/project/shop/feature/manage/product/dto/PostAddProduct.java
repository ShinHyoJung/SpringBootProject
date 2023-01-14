package com.project.shop.feature.manage.product.dto;

import com.project.shop.feature.manage.product.entity.Product;
import lombok.Data;

/**
 * <pre>
 * Created with IntelliJ IDEA.
 * Company: NANDSOFT
 * User: sljh1020
 * Date: 2023-01-04
 * Comments:
 * </pre>
 */
@Data
public class PostAddProduct {
    private String name;
    private int fullQuantity;
    private String category;
    private String info;

    public Product toEntity(String code, String thumbnailImageName) {
        Product product = new Product();
        product.setCode(code);
        product.setName(this.name);
        product.setFullQuantity(this.fullQuantity);
        product.setInfo(this.info);
        product.setCategory(this.category);
        product.setThumbnailImageName(thumbnailImageName);
        return product;
    }
}
