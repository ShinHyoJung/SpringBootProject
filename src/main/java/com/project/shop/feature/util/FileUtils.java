package com.project.shop.feature.util;

import com.project.shop.feature.image.entity.Image;
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

    public static List<Image> parseImage(MultipartHttpServletRequest multipartHttpServletRequest) throws IOException {
        if(ObjectUtils.isEmpty(multipartHttpServletRequest)) {
            return null;
        }

        List<Image> imageList = new ArrayList<>();
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
            for(int i = 0; i < multipartFileList.size(); i++) {
                if(multipartFileList.get(i).isEmpty() == false) {
                    contentType = multipartFileList.get(i).getContentType();
                    if(ObjectUtils.isEmpty(contentType)) {
                        break;
                    } else {
                        originalFileName = multipartFileList.get(i).getOriginalFilename();
                        originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
                    }
                    Image image = new Image();
                    storedFileName = Long.toString(System.nanoTime()) + "type" + String.valueOf(i) + originalFileExtension;
                    image.setSize(String.valueOf(multipartFileList.get(i).getSize()));
                    image.setOrgName(multipartFileList.get(i).getOriginalFilename());
                    image.setStoredName(storedFileName);
                    image.setPath(path + storedFileName);
                    image.setDeleteYN("N");
                    imageList.add(image);

                    file = new File(path + storedFileName);
                    multipartFileList.get(i).transferTo(file);
                }
            }
        }
        return imageList;
    }
}
