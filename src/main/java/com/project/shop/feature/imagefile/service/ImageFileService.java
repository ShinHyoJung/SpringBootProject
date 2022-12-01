package com.project.shop.feature.imagefile.service;

import com.project.shop.feature.imagefile.entity.ImageFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public interface ImageFileService {

    void insert(ImageFile imageFile);

    ImageFile select(int sellID);

    HashMap<String, String> makeThumbnail(String storedName) throws IOException;

    String makeDetail(String storedName) throws IOException;
}
