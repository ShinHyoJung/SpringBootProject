package com.project.shop.feature.manage.product.dao;

import com.project.shop.feature.page.Paging;
import com.project.shop.feature.manage.product.entity.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
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
        String sql = "INSERT INTO product(code, name, full_quantity, sold_quantity, left_quantity, info," +
                "category, thumbnail_image_name, register_date, update_date) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql, product.getCode(), product.getName(), product.getFullQuantity(), 0, product.getFullQuantity(),
                product.getInfo(), product.getCategory(), product.getThumbnailImageName(),
                Timestamp.valueOf(LocalDateTime.now()), Timestamp.valueOf(LocalDateTime.now()));
    }

    public int count(String searchOption, String keyword) throws SQLException {
        String sql = "SELECT COUNT(*) FROM product WHERE 1=1 AND " + searchOption + " LIKE concat('%', ?, '%')";
        int total = jdbcTemplate.queryForObject(sql, new Object[]{keyword}, Integer.class);
        return total;
    }

    public List<Product> selectAll(Paging paging, String searchOption, String keyword) throws SQLException {
        String sql = "SELECT * FROM product " +
                "WHERE 1=1 AND " + searchOption + " LIKE concat('%', ?, '%') LIMIT ?, ?";

        List<Product> productList = jdbcTemplate.query(sql, new Object[]{keyword, paging.getSkip(),
                paging.getCountPerPage()}, new RowMapper<Product>() {
            @Override
            public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
                Product product = new Product();
                product.setProductID(rs.getInt("product_id"));
                product.setName(rs.getString("name"));
                product.setCode(rs.getString("code"));
                product.setCategory(rs.getString("category"));
                product.setThumbnailImageName("thumbnail_image_name");
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
                product.setProductID(rs.getInt("product_id"));
                product.setName(rs.getString("name"));
                product.setCode(rs.getString("code"));
                product.setCategory(rs.getString("category"));
                product.setThumbnailImageName("thumbnail_image_name");
                product.setFullQuantity(rs.getInt("full_quantity"));
                product.setSoldQuantity(rs.getInt("sold_quantity"));
                product.setLeftQuantity(rs.getInt("left_quantity"));
                product.setInfo(rs.getString("info"));
                product.setRegisterDate(rs.getDate("register_date"));
                product.setUpdateDate(rs.getDate("update_date"));
                return product;
            }
        });
        return product;
    }

    public void delete(int productID) {
        String sql = "DELETE FROM product WHERE 1=1 AND product_id = ?";

        jdbcTemplate.update(sql, new Object[]{productID});
    }

    public void update(Product product) {
        String sql = "UPDATE product SET name = ?, info = ?, thumbnail_image_name = ?, full_quantity = ?, " +
                "sold_quantity = ?, left_quantity = ?, update_date = ? " +
                "WHERE 1=1 AND product_id = ?";

        jdbcTemplate.update(sql, new Object[]{product.getName(), product.getInfo(), product.getThumbnailImageName(), product.getFullQuantity(),
                product.getSoldQuantity(), product.getLeftQuantity(), Timestamp.valueOf(LocalDateTime.now()), product.getProductID()});
    }

    public int selectMaxProductID() {
        String sql = "SELECT MAX(product_id)" +
                "FROM product";
        int maxProductID = jdbcTemplate.queryForObject(sql, Integer.class);
        return maxProductID;
    }
}
