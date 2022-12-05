package com.project.shop.feature.imagefile.service.impl;

import com.project.shop.feature.imagefile.dao.mapper.ImageFileMapper;
import com.project.shop.feature.imagefile.entity.ImageFile;
import com.project.shop.feature.imagefile.service.ImageFileService;
import lombok.RequiredArgsConstructor;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.name.Rename;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@Service("ImageFileService")
@RequiredArgsConstructor
public class DefaultImageFileService implements ImageFileService {

    private final ImageFileMapper imageFileMapper;

    @Override
    public void insert(ImageFile imageFile) {
        imageFileMapper.insert(imageFile);
    }

    @Override
    public ImageFile select(int sellID) {
        return imageFileMapper.select(sellID);
    }

    public HashMap<String, String> makeThumbnail(String storedName) throws IOException {
        String storedImagePath = "src/main/webapp/static/images/";
        String thumbnailImagePath = "src/main/webapp/static/images/thumbnail/";
        File imageFile = new File(storedImagePath, storedName);
        File thumbnailImageFile = new File(thumbnailImagePath);

        Thumbnails.of(imageFile)
                .size(100, 100)
                .toFiles(thumbnailImageFile, Rename.PREFIX_DOT_THUMBNAIL);

        String thumbnailImageName = "thumbnail." + storedName;
        thumbnailImagePath += thumbnailImageName;

        HashMap<String, String> map = new HashMap<>();
        map.put("thumbnailImageName", thumbnailImageName);
        map.put("thumbnailImagePath", thumbnailImagePath);

        return map;
    }

    public String makeDetail(String storedName) throws IOException {
        String storedImagePath = "src/main/webapp/static/images/";
        String detailImagePath = "src/main/webapp/static/images/detail/";

        File imageFile = new File(storedImagePath, storedName);
        File detailImageFile = new File(detailImagePath);

        Thumbnails.of(imageFile)
                .size(400, 400)
                .toFiles(detailImageFile, Rename.NO_CHANGE);

        detailImagePath += storedName;

        return detailImagePath;
    }
}
