package com.project.shop.feature.imagefile.dao;

import com.project.shop.feature.imagefile.entity.Image;
import com.project.shop.feature.spring.config.db.DBAccessInformation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.List;
@Component
@RequiredArgsConstructor
public class ImageFileDAO {
    private final DBAccessInformation DBAccessInformation;
    private Connection connection;
    private PreparedStatement pstmt;

    public void insert(List<Image> imageList) throws SQLException {
        String url = DBAccessInformation.getUrl();
        String username = DBAccessInformation.getUsername();
        String password = DBAccessInformation.getPassword();

        connection = DriverManager.getConnection(url, username, password);
        String sql = "INSERT INTO image(org_name, stored_name, size, product_id, product_code, sell_id, path, create_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        pstmt = connection.prepareStatement(sql);

        for(Image image : imageList) {
            pstmt.setString(1, image.getOrgName());
            pstmt.setString(2, image.getStoredName());
            pstmt.setString(3, image.getSize());
            pstmt.setInt(4, image.getProductID());
            pstmt.setString(5, image.getProductCode());
            pstmt.setInt(6, image.getSellID());
            pstmt.setString(7, image.getPath());
            pstmt.setDate(8, (Date) new java.util.Date());
            pstmt.setString(9, "N");
            pstmt.executeUpdate();
        }
    }

    public Image select(int sellID) throws SQLException {
        String url = DBAccessInformation.getUrl();
        String username = DBAccessInformation.getUsername();
        String password = DBAccessInformation.getPassword();

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
            image.setSize(rs.getString("size"));
        }
        return image;
    }

}
