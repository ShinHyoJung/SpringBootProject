package com.project.shop.feature.imagefile.service;

import com.project.shop.feature.imagefile.entity.Image;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public interface ImageFileService {

    void insert(List<Image> imageList) throws SQLException;

    Image select(int sellID) throws SQLException;

    HashMap<String, String> makeThumbnail(String storedName) throws IOException;

    String makeDetail(String storedName) throws IOException;
}
