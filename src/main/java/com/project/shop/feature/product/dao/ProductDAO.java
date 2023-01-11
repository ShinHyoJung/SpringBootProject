package com.project.shop.feature.product.dao;

import com.project.shop.feature.page.Paging;
import com.project.shop.feature.product.entity.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
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
@Repository
public class ProductDAO {
    private final JdbcTemplate jdbcTemplate;

    public ProductDAO(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void insert(Product product) throws SQLException {
        String sql = "INSERT INTO product(code, name, full_quantity, sold_quantity, left_quantity, " +
                "thumbnail_image_name, register_date, update_date) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        Date now = new Date();
        long nowDate = now.getDate();

        jdbcTemplate.update(sql, product.getCode(), product.getName(), product.getFullQuantity(), 0, 0,
                product.getThumbnailImageName(), new java.sql.Date(nowDate), new java.sql.Date(nowDate));
    }

    public int count() throws SQLException {
        String sql = "SELECT COUNT(*) FROM product";
        int total = jdbcTemplate.queryForObject(sql, Integer.class);
        return total;
    }

    public List<Product> selectAll(Paging paging) throws SQLException {
        String sql = "SELECT * FROM product " +
                "LIMIT ?, ?";

        List<Product> productList = jdbcTemplate.query(sql, new Object[]{paging.getSkip(),
                paging.getAmount()}, new RowMapper<Product>() {
            @Override
            public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
                Product product = new Product();
                product.setProductID(rs.getInt("product_id"));
                product.setName(rs.getString("name"));
                product.setCode(rs.getString("code"));
                product.setFullQuantity(rs.getInt("full_quantity"));
                product.setSoldQuantity(rs.getInt("sold_quantity"));
                product.setLeftQuantity(rs.getInt("left_quantity"));
                product.setThumbnailImageName(rs.getString("thumbnail_image_name"));
                return product;
            }
        });
        return productList;
    }

    public Product select(int productID) throws SQLException {
        String sql = "SELECT * FROM product WHERE 1=1 AND product_id = ?";
        Product product = jdbcTemplate.queryForObject(sql, new Object[]{productID}, new RowMapper<Product>() {
            @Override
            public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
                Product product = new Product();
                product.setName(rs.getString("name"));
                product.setCode(rs.getString("code"));
                product.setFullQuantity(rs.getInt("full_quantity"));
                product.setSoldQuantity(rs.getInt("sold_quantity"));
                product.setLeftQuantity(rs.getInt("left_quantity"));
                product.setRegisterDate(rs.getDate("register_date"));
                product.setUpdateDate(rs.getDate("update_date"));
                return product;
            }
        });
        return product;
    }
}
