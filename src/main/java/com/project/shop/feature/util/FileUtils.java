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

    public static List<SellImage> parseInsertSellImage(MultipartHttpServletRequest multipartHttpServletRequest) throws IOException {
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
                    sellImageList.add(sellImage);

                    file = new File(path + storedFileName);
                    multipartFile.transferTo(file);
                }
            }
        }
        return sellImageList;
    }

    public static List<SellImage> parseUpdateSellImage(List<SellImage> sellImageList, MultipartHttpServletRequest multipartHttpServletRequest) throws IOException {
        if(ObjectUtils.isEmpty(multipartHttpServletRequest)) {
            return null;
        }

        Iterator<String> iterator = multipartHttpServletRequest.getFileNames();

        String originalFileName;
        String storedFileName;
        String originalFileExtension;
        String contentType;
        String path = "src/main/webapp/static/images/";
        File file = new File(path);

        List<SellImage> updateSellImageList = new ArrayList<>();

        if(iterator.hasNext()) {
            List<MultipartFile> multipartFileList = multipartHttpServletRequest.getFiles(iterator.next());

            if(multipartFileList != null) {
                for(int i = 0; i < multipartFileList.size(); i++) {
                    if(multipartFileList.get(i).isEmpty() == false) {
                        contentType = multipartFileList.get(i).getContentType();
                        if(ObjectUtils.isEmpty(contentType)) {
                            break;
                        } else {
                            originalFileName = multipartFileList.get(i).getOriginalFilename();
                            originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
                        }

                        if(multipartFileList.get(i).getOriginalFilename() != sellImageList.get(i).getOrgName()) {
                            SellImage sellImage = new SellImage();
                            storedFileName = Long.toString(System.nanoTime()) + originalFileExtension;
                            sellImage.setSellID(sellImageList.get(i).getSellID());
                            sellImage.setSize(String.valueOf(multipartFileList.get(i).getSize()));
                            sellImage.setOrgName(multipartFileList.get(i).getOriginalFilename());
                            sellImage.setStoredName(storedFileName);
                            sellImage.setPath(path + storedFileName);
                            updateSellImageList.add(sellImage);

                            file = new File(path + storedFileName);
                            multipartFileList.get(i).transferTo(file);
                        }
                    } else {
                        updateSellImageList.add(sellImageList.get(i));
                    }
                }
            }
        }
        return updateSellImageList;
    }

    public static List<ProductImage> parseInsertProductImage(MultipartHttpServletRequest multipartHttpServletRequest) throws IOException {
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
                    productImageList.add(productImage);

                    file = new File(path + storedFileName);
                    multipartFile.transferTo(file);
                }
            }
        }
        return productImageList;
    }

    public static List<ProductImage> parseUpdateProductImage(List<ProductImage> productImageList, MultipartHttpServletRequest multipartHttpServletRequest) throws IOException {
        if(ObjectUtils.isEmpty(multipartHttpServletRequest)) {
            return null;
        }

        List<ProductImage> updateProductImageList = new ArrayList<>();

        String path = "src/main/webapp/static/images/";
        File file = new File(path);

        if(file.exists() == false) {
            file.mkdirs();
        }

        String originalFileName;
        String storedFileName;
        String originalFileExtension;
        String contentType;

        Iterator<String> iterator = multipartHttpServletRequest.getFileNames();

        while(iterator.hasNext()) {
            List<MultipartFile> multipartFileList = multipartHttpServletRequest.getFiles(iterator.next());
            for(int i = 0; i < multipartFileList.size(); i++) {
                if(multipartFileList.get(i).isEmpty() == false) {
                    contentType = multipartFileList.get(i).getContentType();
                    if(ObjectUtils.isEmpty(contentType)) {
                        break;
                    } else {
                        originalFileName = multipartFileList.get(i).getOriginalFilename();
                        originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
                    }

                    if(multipartFileList.get(i).getOriginalFilename() != productImageList.get(i).getOrgName()) {
                        ProductImage productImage = new ProductImage();
                        storedFileName = Long.toString(System.nanoTime()) + originalFileExtension;
                        productImage.setProductID(productImageList.get(i).getProductID());
                        productImage.setSize(String.valueOf(multipartFileList.get(i).getSize()));
                        productImage.setOrgName(multipartFileList.get(i).getOriginalFilename());
                        productImage.setStoredName(storedFileName);
                        productImage.setPath(path + storedFileName);
                        updateProductImageList.add(productImage);

                        file = new File(path + storedFileName);
                        multipartFileList.get(i).transferTo(file);
                    }
                } else {
                    updateProductImageList.add(productImageList.get(i));
                }
            }
        }
        return updateProductImageList;
    }
}
