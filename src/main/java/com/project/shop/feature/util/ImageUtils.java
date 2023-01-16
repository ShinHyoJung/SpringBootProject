package com.project.shop.feature.util;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.name.Rename;

import java.io.File;
import java.io.IOException;

public class ImageUtils {
    private static final String ORG_IMAGE_PATH = "src/main/webapp/static/images/";

    public static void cutImage(String storedName, int width, int height) throws IOException, InterruptedException {
        //int cutWidth = width;
        //int cutHeight = height;
        File imageFile = new File(ORG_IMAGE_PATH, storedName);
        File cutImageFile = new File("src/main/webapp/static/images/cut/");

        Thumbnails.of(imageFile)
                .forceSize(width, height)
                .toFiles(cutImageFile, Rename.NO_CHANGE);
        /*
        BufferedImage bufferedOrgImage = ImageIO.read(imageFile);
        BufferedImage bufferedThumbnailImage = new BufferedImage(cutWidth, cutHeight, BufferedImage.TYPE_3BYTE_BGR);
        Graphics2D graphics2D = bufferedOrgImage.createGraphics();

        //java.awt.Image imgTarget = bufferedOrgImage.getScaledInstance(thumbnailWidth, thumbnailHeight, java.awt.Image.SCALE_SMOOTH);

        graphics2D.drawImage(bufferedOrgImage, 0, 0, cutWidth, cutHeight, null);
        ImageIO.write(bufferedThumbnailImage, "png", cutImageFile);
         */
    }

    // 상세이미지 만들기
    public static void resizeImage(String storedName, int width, int height) throws IOException {
        File imageFile = new File(ORG_IMAGE_PATH, storedName);
        File resizeImageFile = new File("src/main/webapp/static/images/resize/");

        Thumbnails.of(imageFile)
                .size(width, height)
                .toFiles(resizeImageFile, Rename.NO_CHANGE);
    }
}
