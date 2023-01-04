package com.project.shop.feature.product.service.impl;

import com.project.shop.feature.product.dao.ProductDAO;
import com.project.shop.feature.product.entity.Product;
import com.project.shop.feature.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * <pre>
 * Created with IntelliJ IDEA.
 * Company: NANDSOFT
 * User: sljh1020
 * Date: 2023-01-04
 * Comments:
 * </pre>
 */
@Service("ProductService")
@RequiredArgsConstructor
public class DefaultProductService implements ProductService {
    private final ProductDAO productDAO;
    @Override
    public void insert(Product product) {

    }

    @Override
    public Product select(int productID) {
        return null;
    }
}
