package com.project.shop.feature.product.service;

import com.project.shop.feature.product.entity.Product;

/**
 * <pre>
 * Created with IntelliJ IDEA.
 * Company: NANDSOFT
 * User: sljh1020
 * Date: 2023-01-04
 * Comments:
 * </pre>
 */
public interface ProductService {

    void insert(Product product);

    Product select(int productID);
}
