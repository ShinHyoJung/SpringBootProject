package com.project.shop.feature.image.sellimage.dao;

import com.project.shop.feature.image.sellimage.entity.SellImage;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.sql.*;
import java.util.List;

@Repository
public class SellImageDAO {
    private final JdbcTemplate jdbcTemplate;

    public SellImageDAO(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void insert(List<SellImage> sellImageList) throws SQLException {
        String sql = "INSERT INTO sell_image(org_name, stored_name, size," +
                "path, sell_id, create_date, delete_yn) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        for(SellImage sellImage : sellImageList) {
            jdbcTemplate.update(sql, sellImage.getOrgName(), sellImage.getStoredName(), sellImage.getSize(),
                    sellImage.getPath(), sellImage.getSellID(), Timestamp.valueOf(LocalDateTime.now()), "N");
        }
    }

    public SellImage select(int sellID) throws SQLException {
        String sql = "SELECT * FROM sell_image WHERE 1=1 AND sell_id = ? AND delete_yn = ?";
        SellImage sellImage = jdbcTemplate.queryForObject(sql, new Object[]{sellID, "N"}, new RowMapper<SellImage>() {
            @Override
            public SellImage mapRow(ResultSet rs, int rowNum) throws SQLException {
                    SellImage sellImage = new SellImage();
                    sellImage.setImageID(rs.getInt("image_id"));
                    sellImage.setOrgName(rs.getString("org_name"));
                    sellImage.setStoredName(rs.getString("stored_name"));
                    sellImage.setSize(rs.getString("size"));
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
