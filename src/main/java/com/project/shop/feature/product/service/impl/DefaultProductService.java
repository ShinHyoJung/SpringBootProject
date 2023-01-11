package com.project.shop.feature.product.service.impl;

import com.project.shop.feature.page.Paging;
import com.project.shop.feature.product.dao.ProductDAO;
import com.project.shop.feature.product.entity.Product;
import com.project.shop.feature.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

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
    public void insert(Product product) throws SQLException {
        productDAO.insert(product);
    }

    @Override
    public int count() throws SQLException {
        return productDAO.count();
    }


    @Override
    public List<Product> selectAll(Paging paging) throws SQLException {
        return productDAO.selectAll(paging);
    }

    @Override
    public Product select(int productID) throws SQLException {
        return productDAO.select(productID);
    }
}
