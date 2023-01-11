package com.project.shop.feature.image.dao;

import com.project.shop.feature.image.entity.Image;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

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

    public void insert(List<Image> imageList) throws SQLException {
        String sql = "INSERT INTO image(org_name, stored_name, size," +
                "path, thumbnail_image_name, thumbnail_image_path, " +
                "detail_image_name, detail_image_path," +
                "product_id, product_code, sell_id, create_date, delete_yn) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ,?)";
        Date now = new Date();
        long date = now.getDate();

        for(Image image : imageList) {
            jdbcTemplate.update(sql, image.getOrgName(), image.getStoredName(), image.getSize(),
                    image.getPath(), image.getThumbnailImageName(), image.getThumbnailImagePath(),
                    image.getDetailImageName(), image.getDetailImagePath(), image.getProductID(),
                    image.getProductCode(), image.getSellID(), new java.sql.Date(date), "N");
        }
    }

    public Image select(int sellID) throws SQLException {
        String sql = "SELECT * FROM image WHERE 1=1 AND sell_id = ? AND delete_yn = ?";
        Image image = jdbcTemplate.queryForObject(sql, new Object[]{sellID, "N"}, new RowMapper<Image>() {
            @Override
            public Image mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Image image = new Image();
                    image.setImageID(rs.getInt("image_id"));
                    image.setOrgName(rs.getString("org_name"));
                    image.setStoredName(rs.getString("stored_name"));
                    image.setSize(rs.getString("size"));
                    image.setThumbnailImageName(rs.getString("thumbnail_image_name"));
                    image.setThumbnailImagePath(rs.getString("thumbnail_image_path"));
                    image.setDetailImageName(rs.getString("detail_image_name"));
                    image.setDetailImagePath(rs.getString("detail_image_path"));
                    image.setProductID(rs.getInt("product_id"));
                    image.setProductCode(rs.getString("product_code"));
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
