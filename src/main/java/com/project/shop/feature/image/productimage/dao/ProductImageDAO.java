package com.project.shop.feature.image.productimage.dao;

import com.project.shop.feature.image.productimage.entity.ProductImage;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class ProductImageDAO {
    private final JdbcTemplate jdbcTemplate;

    public ProductImageDAO(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void insert(List<ProductImage> productImageList) {
        String sql = "INSERT INTO product_image (org_name, stored_name, size, product_id, path, create_date, delete_yn)" +
                " VALUES (?, ?, ?, ?, ?, ?, ?)";
        for(ProductImage productImage : productImageList) {
            jdbcTemplate.update(sql, productImage.getOrgName(), productImage.getStoredName(), productImage.getSize(),
                    productImage.getProductID(), productImage.getPath(), Timestamp.valueOf(LocalDateTime.now()), "N");
        }
    }

    public List<ProductImage> select(int productID) {
        String sql = "SELECT * FROM product_image WHERE 1=1 AND product_id = ?";

        List<ProductImage> productImageList = jdbcTemplate.query(sql, new Object[]{productID}, new RowMapper<ProductImage>() {
            @Override
            public ProductImage mapRow(ResultSet rs, int rowNum) throws SQLException {
                ProductImage productImage = new ProductImage();
                productImage.setImageID(rs.getInt("image_id"));
                productImage.setOrgName(rs.getString("org_name"));
                productImage.setStoredName(rs.getString("stored_name"));
                productImage.setSize(rs.getString("size"));
                productImage.setProductID(rs.getInt("product_id"));
                productImage.setPath(rs.getString("path"));
                productImage.setCreateDate(rs.getDate("create_date"));
                productImage.setDeleteYN(rs.getString("delete_yn"));
                return productImage;
            }
        });
        return productImageList;
    }
}
