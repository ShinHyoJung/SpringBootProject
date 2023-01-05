package com.project.shop.feature.product.dao;

import com.project.shop.feature.page.Paging;
import com.project.shop.feature.product.entity.Product;
import com.project.shop.feature.spring.config.db.DBConnection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.Date;
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
@Component
@RequiredArgsConstructor
public class ProductDAO {
    private final DBConnection dbConnection;
    private Connection connection;
    private PreparedStatement pstmt;
    public void insert(Product product) throws SQLException {
        String url = dbConnection.getUrl();
        String username = dbConnection.getUsername();
        String password = dbConnection.getPassword();

        connection = DriverManager.getConnection(url, username, password);

        String sql = "INSERT INTO product(code, name, full_quantity, sold_quantity, left_quantity, image, register_date, update_date) VALUES (?, ?, ?, ?, ?, ?, ?,,?)";

        pstmt = connection.prepareStatement(sql);

        Date now = new Date();
        long date = now.getDate();

        pstmt.setString(1, product.getCode());
        pstmt.setString(2, product.getName());
        pstmt.setInt(3, product.getFullQuantity());
        pstmt.setInt(4, 0);
        pstmt.setInt(5, 0);
        pstmt.setString(6, product.getImage());
        pstmt.setDate(7, new java.sql.Date(date));
        pstmt.setDate(8, new java.sql.Date(date));
        pstmt.executeUpdate();
    }

    public int count() throws SQLException {
        String url = dbConnection.getUrl();
        String username = dbConnection.getUsername();
        String password = dbConnection.getPassword();

        connection = DriverManager.getConnection(url, username, password);
        String sql = "SELECT COUNT(*) FROM product";
        pstmt = connection.prepareStatement(sql);

        ResultSet rs = pstmt.executeQuery();
        rs.next();
        int total = rs.getInt(1);
        return total;
    }
    public List<Product> selectAll(Paging paging) throws SQLException {
        String url = dbConnection.getUrl();
        String username = dbConnection.getUsername();
        String password = dbConnection.getPassword();

        connection = DriverManager.getConnection(url, username, password);
        String sql = "SELECT * FROM product" +
                "LIMIT '" + paging.getSkip() + "', '" + paging.getAmount() + "'";
        pstmt = connection.prepareStatement(sql);

        List<Product> productList = null;
        Product product = new Product();
        ResultSet rs = pstmt.executeQuery();

        while(rs.next()) {
            product.setProductID(rs.getInt("product_id"));
            product.setCode(rs.getString("code"));
            product.setName(rs.getString("name"));
            product.setImage(rs.getString("image"));
            product.setFullQuantity(rs.getInt("full_quantity"));
            product.setSoldQuantity(rs.getInt("sold_quantity"));
            product.setLeftQuantity(rs.getInt("left_quantity"));
            productList.add(product);
        }
        return productList;
    }

    public Product select(int sellID) throws SQLException {
        String url = dbConnection.getUrl();
        String username = dbConnection.getUsername();
        String password = dbConnection.getPassword();

        connection = DriverManager.getConnection(url, username, password);
        Product product = null;
        String sql = "SELECT * FROM product WHERE 1=1 AND product_id = '" + sellID +"'";
        pstmt = connection.prepareStatement(sql);

        ResultSet rs = pstmt.executeQuery();

        while(rs.next()) {
            product.setProductID(rs.getInt("product_id"));
            product.setCode(rs.getString("code"));
            product.setName(rs.getString("name"));
            product.setFullQuantity(rs.getInt("full_quantity"));
            product.setImage(rs.getString("image"));
            product.setRegisterDate(rs.getDate("register_date"));
            product.setUpdateDate(rs.getDate("update_date"));
        }
        return product;
    }
}
