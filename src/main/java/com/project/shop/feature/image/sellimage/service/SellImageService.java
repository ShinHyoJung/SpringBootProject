package com.project.shop.feature.image.sellimage.service;

import com.project.shop.feature.image.sellimage.entity.SellImage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface SellImageService {

    void insert(List<SellImage> sellImageList) throws SQLException;

    List<SellImage> select(int sellID) throws SQLException;

    void update(SellImage sellImage);
}
