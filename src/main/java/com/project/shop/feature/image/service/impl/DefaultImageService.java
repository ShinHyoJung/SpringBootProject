package com.project.shop.feature.image.service.impl;

import com.project.shop.feature.image.dao.ImageDAO;
import com.project.shop.feature.image.entity.Image;
import com.project.shop.feature.image.service.ImageService;
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
public class DefaultImageService implements ImageService {

    private final ImageDAO imageDAO;
    @Override
    public void insert(List<Image> imageList) throws SQLException {
        imageDAO.insert(imageList);
    }

    @Override
    public Image select(int sellID) throws SQLException {
        return imageDAO.select(sellID);
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
