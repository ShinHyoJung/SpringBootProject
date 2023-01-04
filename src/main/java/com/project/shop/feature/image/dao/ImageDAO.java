package com.project.shop.feature.image.dao;

import com.project.shop.feature.image.entity.Image;
import com.project.shop.feature.spring.config.db.DBConnection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.sql.*;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ImageDAO {
    private final DBConnection DBConnection;
    private Connection connection;
    private PreparedStatement pstmt;

    public void insert(List<Image> imageList) throws SQLException {
        String url = DBConnection.getUrl();
        String username = DBConnection.getUsername();
        String password = DBConnection.getPassword();

        connection = DriverManager.getConnection(url, username, password);
        String sql = "INSERT INTO image(org_name, stored_name, size," +
                "path, thumbnail_image_name, thumbnail_image_path, " +
                "detail_image_name, detail_image_path," +
                "product_id, product_code, sell_id, create_date, delete_yn) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ,?)";

        pstmt = connection.prepareStatement(sql);

        Date now = new Date();
        long time = now.getDate();

        for(Image image : imageList) {
            pstmt.setString(1, image.getOrgName());
            pstmt.setString(2, image.getStoredName());
            pstmt.setString(3, image.getSize());
            pstmt.setString(4, image.getPath());
            pstmt.setString(5, image.getThumbnailImageName());
            pstmt.setString(6, image.getThumbnailImagePath());
            pstmt.setString(7, image.getDetailImageName());
            pstmt.setString(8, image.getDetailImagePath());
            pstmt.setInt(9, image.getProductID());
            pstmt.setString(10, image.getProductCode());
            pstmt.setInt(11, image.getSellID());
            pstmt.setDate(12, new java.sql.Date(time));
            pstmt.setString(13, "N");
            pstmt.executeUpdate();
        }
    }

    public Image select(int sellID) throws SQLException {
        String url = DBConnection.getUrl();
        String username = DBConnection.getUsername();
        String password = DBConnection.getPassword();

        Image image = null;
        connection = DriverManager.getConnection(url, username, password);
        String sql = "SELECT * FROM image WHERE 1=1 AND sell_id = '" + sellID + "' AND delete_yn = 'N'";

        pstmt = connection.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();

        while(rs.next()) {
            image = new Image();
            image.setImageID(rs.getInt("image_id"));
            image.setOrgName(rs.getString("org_name"));
            image.setStoredName(rs.getString("stored_name"));
            image.setDetailImageName(rs.getString("detail_image_name"));
            image.setSize(rs.getString("size"));
        }
        return image;
    }
}
