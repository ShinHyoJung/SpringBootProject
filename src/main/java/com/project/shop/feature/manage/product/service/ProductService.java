package com.project.shop.feature.manage.product.service;

import com.project.shop.feature.page.Paging;
import com.project.shop.feature.manage.product.entity.Product;

import java.sql.SQLException;
import java.util.List;

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

    void insert(Product product) throws SQLException;

    int count(String searchOption, String keyword) throws SQLException;

    List<Product> selectAll(Paging paging, String searchOption, String keyword) throws SQLException;

    Product select(int productID) throws SQLException;

    String makeProductCode();

    void delete(int productID);

    void update(Product product);

    int selectMaxProductID();
}
