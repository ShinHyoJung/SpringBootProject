package com.project.shop.feature.image.productimage.service;

import com.project.shop.feature.image.productimage.entity.ProductImage;

import java.util.List;

public interface ProductImageService {

    void insert(List<ProductImage> productImageList);

    List<ProductImage> select(int productID);

    void update(ProductImage productImage);
}
