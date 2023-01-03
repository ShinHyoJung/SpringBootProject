package com.project.shop.feature.imagefile.service.impl;

import com.project.shop.feature.imagefile.dao.ImageFileDAO;
import com.project.shop.feature.imagefile.entity.Image;
import com.project.shop.feature.imagefile.service.ImageFileService;
import lombok.RequiredArgsConstructor;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.name.Rename;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;


@Service("ImageFileService")
@RequiredArgsConstructor
public class DefaultImageFileService implements ImageFileService {

    private final ImageFileDAO imageFileDAO;
    @Override
    public void insert(List<Image> imageList) throws SQLException {
        imageFileDAO.insert(imageList);
    }

    @Override
    public Image select(int sellID) throws SQLException {
        return imageFileDAO.select(sellID);
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
