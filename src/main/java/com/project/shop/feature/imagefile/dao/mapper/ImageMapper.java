package com.project.shop.feature.imagefile.dao.mapper;

import com.project.shop.feature.imagefile.entity.Image;
import org.apache.ibatis.annotations.*;

@Mapper
public interface ImageMapper {

    @Insert(
            "INSERT INTO image ( \n" +
                    "org_name, \n" +
                    "stored_name, \n" +
                    "size, \n" +
                    "thumbnail_image_name, \n" +
                    "thumbnail_image_path, \n" +
                    "detail_image_name, \n" +
                    "detail_image_path, \n" +
                    "product_id, \n" +
                    "product_code, \n" +
                    "sell_id, \n" +
                    "path, \n" +
                    "create_date, \n" +
                    "delete_yn \n" +
                    ") VALUES ( \n" +
                    "#{orgName}, \n" +
                    "#{storedName}, \n" +
                    "#{size}, \n" +
                    "#{thumbnailImageName}, \n" +
                    "#{thumbnailImagePath}, \n" +
                    "#{detailImageName}, \n" +
                    "#{detailImagePath}, \n" +
                    "#{productID}, \n" +
                    "#{productCode}, \n" +
                    "#{sellID}, \n" +
                    "#{path}, \n" +
                    "NOW(), \n" +
                    "'N' \n" +
                    ") \n"
    )
    void insert(Image image);

    @Select(
            "SELECT image_id AS imageID, \n" +
                    "org_name AS orgName, \n" +
                    "stored_name AS storedName, \n" +
                    "size AS size, \n" +
                    "thumbnail_image_name AS thumbnailImageName, \n" +
                    "thumbnail_image_path AS thumbnailImagePath, \n" +
                    "detail_image_name AS detailImageName, \n" +
                    "detail_image_path AS detailImagePath \n" +
                    "FROM image \n" +
                    "WHERE 1=1 \n" +
                    "AND sell_id = #{sellID} \n" +
                    "AND delete_yn = 'N' \n"
    )
    Image select(int sellID);
}
