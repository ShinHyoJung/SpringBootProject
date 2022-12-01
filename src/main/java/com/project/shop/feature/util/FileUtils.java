package com.project.shop.feature.util;

import com.project.shop.feature.imagefile.entity.ImageFile;
import com.project.shop.feature.sell.entity.Sell;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.name.Rename;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class FileUtils {

    public List<ImageFile> parseFile(Sell sell, MultipartHttpServletRequest multipartHttpServletRequest) throws IOException {
        if(ObjectUtils.isEmpty(multipartHttpServletRequest)) {
            return null;
        }

        List<ImageFile> fileList = new ArrayList<>();
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
                    ImageFile imageFile = new ImageFile();
                    imageFile.setSellID(sell.getSellID());
                    imageFile.setSize(String.valueOf(multipartFile.getSize()));
                    imageFile.setOrgName(multipartFile.getOriginalFilename());
                    imageFile.setStoredName(storedFileName);
                    imageFile.setPath(path + storedFileName);
                    imageFile.setProductCode(sell.getProductCode());
                    imageFile.setDeleteYN("N");
                    fileList.add(imageFile);

                    file = new File(path + storedFileName);
                    multipartFile.transferTo(file);
                }
            }
        }
        return fileList;
    }
}
