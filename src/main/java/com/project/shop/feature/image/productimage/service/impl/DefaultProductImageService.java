package com.project.shop.feature.image.productimage.service.impl;

import com.project.shop.feature.image.productimage.dao.ProductImageDAO;
import com.project.shop.feature.image.productimage.entity.ProductImage;
import com.project.shop.feature.image.productimage.service.ProductImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ProductImageService")
@RequiredArgsConstructor
public class DefaultProductImageService implements ProductImageService {

    private final ProductImageDAO productImageDAO;
    @Override
    public void insert(List<ProductImage> productImageList) {
        productImageDAO.insert(productImageList);
    }

    @Override
    public List<ProductImage> select(int productID) {
        return productImageDAO.select(productID);
    }
}
