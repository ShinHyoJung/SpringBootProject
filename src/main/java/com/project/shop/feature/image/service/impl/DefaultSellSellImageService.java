package com.project.shop.feature.image.service.impl;

import com.project.shop.feature.image.dao.SellImageDAO;
import com.project.shop.feature.image.entity.SellImage;
import com.project.shop.feature.image.service.SellImageService;
import lombok.RequiredArgsConstructor;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.name.Rename;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;


@Service("ImageFileService")
@RequiredArgsConstructor
public class DefaultSellSellImageService implements SellImageService {
    private static final String ORG_IMAGE_PATH = "src/main/webapp/static/images/";

    private final SellImageDAO sellImageDAO;
    @Override
    public void insert(SellImage sellImage) throws SQLException {
        sellImageDAO.insert(sellImage);
    }

    @Override
    public SellImage select(int sellID, int type) throws SQLException {
        return sellImageDAO.select(sellID, type);
    }

    // 썸네일 이미지 만들기
    public void makeThumbnailImage(String storedName) throws IOException, InterruptedException {
        int thumbnailWidth = 150;
        int thumbnailHeight = 100;
        String thumbnailImageName = "thumbnail." + storedName;
        File imageFile = new File(ORG_IMAGE_PATH + storedName);
        File thumbnailImageFile = new File("src/main/webapp/static/images/thumbnail/" + thumbnailImageName);

        BufferedImage bufferedOrgImage = ImageIO.read(imageFile);
        java.awt.Image imgTarget = bufferedOrgImage.getScaledInstance(thumbnailWidth, thumbnailHeight, java.awt.Image.SCALE_SMOOTH);
        int pixels[] = new int[thumbnailWidth * thumbnailHeight];
        PixelGrabber pg = new PixelGrabber(imgTarget, 0, 0, thumbnailWidth, thumbnailHeight, pixels, 0, thumbnailWidth);
        pg.grabPixels();
        BufferedImage bufferThumbnailImage = new BufferedImage(thumbnailWidth, thumbnailHeight, BufferedImage.TYPE_INT_RGB);

        Graphics2D graphics2D = bufferedOrgImage.createGraphics();
        graphics2D.drawImage(bufferedOrgImage, 0, 0, thumbnailWidth, thumbnailHeight, null);
        ImageIO.write(bufferThumbnailImage, "png", thumbnailImageFile);
    }

    //판매글 제목 이미지 만들기
    public void makeTitleImage(String storedName) throws IOException, InterruptedException {
        int titleWidth = 300;
        int titleHeight = 300;
        String titleImageName = "title." + storedName;

        File imageFile = new File(ORG_IMAGE_PATH, storedName);
        File titleImageFile = new File("src/main/webapp/static/images/title/" + titleImageName);

        BufferedImage bufferedOrgImage = ImageIO.read(imageFile);
        java.awt.Image imgTarget = bufferedOrgImage.getScaledInstance(titleWidth, titleHeight, java.awt.Image.SCALE_SMOOTH);
        int pixels[] = new int[titleWidth * titleHeight];
        PixelGrabber pg = new PixelGrabber(imgTarget, 0, 0, titleWidth, titleHeight, pixels, 0, titleWidth);
        pg.grabPixels();
        BufferedImage bufferedTitleImage = new BufferedImage(titleWidth, titleHeight, BufferedImage.TYPE_INT_RGB);

        Graphics2D graphics2D = bufferedOrgImage.createGraphics();
        graphics2D.drawImage(bufferedOrgImage, 0, 0, titleWidth, titleHeight, null);
        ImageIO.write(bufferedTitleImage, "png", titleImageFile);
    }

    // 상세이미지 만들기
    public void makeDetailImage(String storedName) throws IOException {
        String detailImageName = "detail." + storedName;

        File imageFile = new File(ORG_IMAGE_PATH, storedName);
        File detailImageFile = new File("src/main/webapp/static/images/detail/" + detailImageName);

        Thumbnails.of(imageFile)
                .size(500, 500)
                .toFiles(detailImageFile, Rename.NO_CHANGE);
    }
}
