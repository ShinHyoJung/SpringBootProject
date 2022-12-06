package com.project.shop.feature.util;

import com.project.shop.feature.imagefile.entity.Image;
import com.project.shop.feature.sell.entity.Sell;
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

    public Image parseFile(Sell sell, MultipartHttpServletRequest multipartHttpServletRequest) throws IOException {
        if(ObjectUtils.isEmpty(multipartHttpServletRequest)) {
            return null;
        }

        //List<Image> fileList = new ArrayList<>();
        Image image = new Image();
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

                    storedFileName = Long.toString(System.nanoTime()) + originalFileExtension;
                    image.setSize(String.valueOf(multipartFile.getSize()));
                    image.setOrgName(multipartFile.getOriginalFilename());
                    image.setStoredName(storedFileName);
                    image.setPath(path + storedFileName);
                    image.setProductCode(sell.getProductCode());
                    image.setDeleteYN("N");

                    file = new File(path + storedFileName);
                    multipartFile.transferTo(file);
                }
            }
        }
        return image;
    }
}
