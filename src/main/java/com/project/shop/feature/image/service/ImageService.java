package com.project.shop.feature.image.service;

import com.project.shop.feature.image.entity.Image;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public interface ImageService {

    void insert(List<Image> imageList) throws SQLException;

    Image select(int sellID) throws SQLException;

    String makeThumbnail(String storedName) throws IOException, InterruptedException;

    String makeDetail(String storedName) throws IOException;
}
