package com.project.shop.feature.image.service;

import com.project.shop.feature.image.entity.Image;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public interface ImageService {

    void insert(List<Image> imageList) throws SQLException;

    Image select(int sellID) throws SQLException;

    String makeThumbnailImage(String storedName) throws IOException, InterruptedException;

    String makeDetailImage(String storedName) throws IOException;
}
