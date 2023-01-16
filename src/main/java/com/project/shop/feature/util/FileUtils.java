package com.project.shop.feature.util;

import com.project.shop.feature.image.productimage.entity.ProductImage;
import com.project.shop.feature.image.sellimage.entity.SellImage;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class FileUtils {

    public static List<SellImage> parseSellImage(MultipartHttpServletRequest multipartHttpServletRequest) throws IOException {
        if(ObjectUtils.isEmpty(multipartHttpServletRequest)) {
            return null;
        }

        List<SellImage> sellImageList = new ArrayList<>();
        String path = "src/main/webapp/static/images/";
        File file = new File(path);

        if(file.exists() == false) {
            file.mkdirs();
        }

        Iterator<String> iterator = multipartHttpServletRequest.getFileNames();

        String originalFileName;
        String storedFileName;
        String originalFileExtension;
        String contentType;

        while(iterator.hasNext()) {
            List<MultipartFile> multipartFileList = multipartHttpServletRequest.getFiles(iterator.next());
            for(MultipartFile multipartFile : multipartFileList) {
                if(multipartFile.isEmpty() == false) {
                    contentType = multipartFile.getContentType();
                    if(ObjectUtils.isEmpty(contentType)) {
                        break;
                    } else {
                        originalFileName = multipartFile.getOriginalFilename();
                        originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
                    }
                    SellImage sellImage = new SellImage();
                    storedFileName = Long.toString(System.nanoTime()) + originalFileExtension;
                    sellImage.setSize(String.valueOf(multipartFile.getSize()));
                    sellImage.setOrgName(multipartFile.getOriginalFilename());
                    sellImage.setStoredName(storedFileName);
                    sellImage.setPath(path + storedFileName);
                    sellImage.setDeleteYN("N");
                    sellImageList.add(sellImage);

                    file = new File(path + storedFileName);
                    multipartFile.transferTo(file);
                }
            }
        }
        return sellImageList;
    }

    public static List<ProductImage> parseProductImage(MultipartHttpServletRequest multipartHttpServletRequest) throws IOException {
        if(ObjectUtils.isEmpty(multipartHttpServletRequest)) {
            return null;
        }

        List<ProductImage> productImageList = new ArrayList<>();
        String path = "src/main/webapp/static/images/";
        File file = new File(path);

        if(file.exists() == false) {
            file.mkdirs();
        }

        Iterator<String> iterator = multipartHttpServletRequest.getFileNames();

        String originalFileName;
        String storedFileName;
        String originalFileExtension;
        String contentType;

        while(iterator.hasNext()) {
            List<MultipartFile> multipartFileList = multipartHttpServletRequest.getFiles(iterator.next());
            for(MultipartFile multipartFile : multipartFileList) {
                if(multipartFile.isEmpty() == false) {
                    contentType = multipartFile.getContentType();
                    if(ObjectUtils.isEmpty(contentType)) {
                        break;
                    } else {
                        originalFileName = multipartFile.getOriginalFilename();
                        originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
                    }
                    ProductImage productImage = new ProductImage();
                    storedFileName = Long.toString(System.nanoTime()) + originalFileExtension;
                    productImage.setSize(String.valueOf(multipartFile.getSize()));
                    productImage.setOrgName(multipartFile.getOriginalFilename());
                    productImage.setStoredName(storedFileName);
                    productImage.setPath(path + storedFileName);
                    productImage.setDeleteYN("N");
                    productImageList.add(productImage);

                    file = new File(path + storedFileName);
                    multipartFile.transferTo(file);
                }
            }
        }
        return productImageList;
    }
}
