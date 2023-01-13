package com.project.shop.feature.image.dao;

import com.project.shop.feature.image.entity.Image;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.sql.DataSource;
import java.util.Date;
import java.sql.*;
import java.util.List;

@Repository
public class ImageDAO {
    private final JdbcTemplate jdbcTemplate;

    public ImageDAO(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void insert(Image image) throws SQLException {
        String sql = "INSERT INTO image(org_name, stored_name, size," +
                "path, thumbnail_image_name, detail_image_name, " +
                "product_id, sell_id, type, create_date, delete_yn) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Date now = new Date();
        long date = now.getDate();

        jdbcTemplate.update(sql, image.getOrgName(), image.getStoredName(), image.getSize(),
                image.getPath(), image.getThumbnailImageName(),
                image.getDetailImageName(), image.getProductID(),
                image.getSellID(), image.getType(), new java.sql.Date(date), "N");
    }

    public Image select(int sellID, int type) throws SQLException {
        String sql = "SELECT * FROM image WHERE 1=1 AND sell_id = ? AND delete_yn = ? AND type = ?";
        Image image = jdbcTemplate.queryForObject(sql, new Object[]{sellID, "N", type}, new RowMapper<Image>() {
            @Override
            public Image mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Image image = new Image();
                    image.setImageID(rs.getInt("image_id"));
                    image.setOrgName(rs.getString("org_name"));
                    image.setStoredName(rs.getString("stored_name"));
                    image.setSize(rs.getString("size"));
                    image.setThumbnailImageName(rs.getString("thumbnail_image_name"));
                    image.setDetailImageName(rs.getString("detail_image_name"));
                    image.setProductID(rs.getInt("product_id"));
                    image.setSellID(rs.getInt("sell_id"));
                    image.setPath(rs.getString("path"));
                    image.setCreateDate(rs.getDate("create_date"));
                    image.setDeleteYN(rs.getString("delete_yn"));
                    return image;
            }
        });
        return image;
    }
}
