package com.project.shop.feature.image.service.impl;

import com.project.shop.feature.image.dao.ImageDAO;
import com.project.shop.feature.image.entity.Image;
import com.project.shop.feature.image.service.ImageService;
import lombok.RequiredArgsConstructor;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import net.coobird.thumbnailator.name.Rename;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
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

    public String makeThumbnail(String storedName) throws IOException, InterruptedException {
        int thumbnail_width = 150;
        int thumbnail_height = 100;
        String thumbnailImageName = "thumbnail." + storedName;
        File imageFile = new File("src/main/webapp/static/images/" + storedName);
        File thumbnailImageFile = new File("src/main/webapp/static/images/thumbnail/" + thumbnailImageName);

        BufferedImage buffer_original_image = ImageIO.read(imageFile);
        java.awt.Image imgTarget = buffer_original_image.getScaledInstance(thumbnail_width, thumbnail_height, java.awt.Image.SCALE_SMOOTH);
        int pixels[] = new int[thumbnail_width * thumbnail_height];
        PixelGrabber pg = new PixelGrabber(imgTarget, 0, 0, thumbnail_width, thumbnail_height, pixels, 0, thumbnail_width);
        pg.grabPixels();
        BufferedImage buffer_thumbnail_image = new BufferedImage(thumbnail_width, thumbnail_height, BufferedImage.TYPE_INT_RGB);
        buffer_thumbnail_image.setRGB(0, 0, thumbnail_width, thumbnail_height, pixels, 0, thumbnail_width);
        Graphics2D graphics2D = buffer_thumbnail_image.createGraphics();
        graphics2D.drawImage(buffer_original_image, 0, 0, thumbnail_width, thumbnail_height, null);
        ImageIO.write(buffer_thumbnail_image, "png", thumbnailImageFile);

        return thumbnailImageName;
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
