package com.project.shop.feature.image.dao;

import com.project.shop.feature.image.entity.SellImage;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.sql.*;

@Repository
public class SellImageDAO {
    private final JdbcTemplate jdbcTemplate;

    public SellImageDAO(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void insert(SellImage sellImage) throws SQLException {
        String sql = "INSERT INTO sell_image(org_name, stored_name, size," +
                "path, thumbnail_image_name, detail_image_name, " +
                "product_id, sell_id, type, create_date, delete_yn) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql, sellImage.getOrgName(), sellImage.getStoredName(), sellImage.getSize(),
                sellImage.getPath(), sellImage.getThumbnailImageName(),
                sellImage.getDetailImageName(), sellImage.getProductID(),
                sellImage.getSellID(), sellImage.getType(), Timestamp.valueOf(LocalDateTime.now()), "N");
    }

    public SellImage select(int sellID, int type) throws SQLException {
        String sql = "SELECT * FROM sell_image WHERE 1=1 AND sell_id = ? AND delete_yn = ? AND type = ?";
        SellImage sellImage = jdbcTemplate.queryForObject(sql, new Object[]{sellID, "N", type}, new RowMapper<SellImage>() {
            @Override
            public SellImage mapRow(ResultSet rs, int rowNum) throws SQLException {
                    SellImage sellImage = new SellImage();
                    sellImage.setImageID(rs.getInt("image_id"));
                    sellImage.setOrgName(rs.getString("org_name"));
                    sellImage.setStoredName(rs.getString("stored_name"));
                    sellImage.setSize(rs.getString("size"));
                    sellImage.setThumbnailImageName(rs.getString("thumbnail_image_name"));
                    sellImage.setDetailImageName(rs.getString("detail_image_name"));
                    sellImage.setProductID(rs.getInt("product_id"));
                    sellImage.setSellID(rs.getInt("sell_id"));
                    sellImage.setPath(rs.getString("path"));
                    sellImage.setCreateDate(rs.getDate("create_date"));
                    sellImage.setDeleteYN(rs.getString("delete_yn"));
                    return sellImage;
            }
        });
        return sellImage;
    }
}
