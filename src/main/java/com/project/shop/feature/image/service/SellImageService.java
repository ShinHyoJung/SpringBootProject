package com.project.shop.feature.image.service;

import com.project.shop.feature.image.entity.SellImage;

import java.io.IOException;
import java.sql.SQLException;

public interface SellImageService {

    void insert(SellImage sellImage) throws SQLException;

    SellImage select(int sellID, int type) throws SQLException;

    void makeThumbnailImage(String storedName) throws IOException, InterruptedException;

    String makeDetailImage(String storedName) throws IOException;
}
