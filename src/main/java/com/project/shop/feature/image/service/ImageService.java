package com.project.shop.feature.image.service;

import com.project.shop.feature.image.entity.Image;

import java.io.IOException;
import java.sql.SQLException;

public interface ImageService {

    void insert(Image image) throws SQLException;

    Image select(int sellID, int type) throws SQLException;

    void makeThumbnailImage(String storedName) throws IOException, InterruptedException;

    String makeDetailImage(String storedName) throws IOException;
}
