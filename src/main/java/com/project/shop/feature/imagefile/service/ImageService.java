package com.project.shop.feature.imagefile.service;

import com.project.shop.feature.imagefile.entity.Image;

import java.io.IOException;
import java.util.HashMap;

public interface ImageService {

    void insert(Image image);

    Image select(int sellID);

    HashMap<String, String> makeThumbnail(String storedName) throws IOException;

    String makeDetail(String storedName) throws IOException;
}
