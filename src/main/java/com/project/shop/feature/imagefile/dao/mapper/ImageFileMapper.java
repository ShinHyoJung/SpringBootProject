package com.project.shop.feature.imagefile.dao.mapper;

import com.project.shop.feature.imagefile.entity.ImageFile;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ImageFileMapper {

    @Insert(
            "INSERT INTO imagefile ( \n" +
                    "org_name, \n" +
                    "stored_name, \n" +
                    "size, \n" +
                    "product_id, \n" +
                    "product_code, \n" +
                    "sell_id, \n" +
                    "path, \n" +
                    "create_date \n" +
                    ") VALUES ( \n" +
                    "#{orgName}, \n" +
                    "#{storedName}, \n" +
                    "#{size}, \n" +
                    "#{productID}, \n" +
                    "#{productCode}, \n" +
                    "#{sellID}, \n" +
                    "#{path}, \n" +
                    "NOW() \n" +
                    ") \n"
    )
    void insert(ImageFile imageFile);

    @Select(
            "SELECT imagefile_id AS imageFileID, \n" +
                    "org_name AS orgName, \n" +
                    "stored_name AS storedName, \n" +
                    "size AS size \n" +
                    "FROM imagefile \n" +
                    "WHERE 1=1 \n" +
                    "AND sell_id = #{sellID} \n" +
                    "AND delete_yn = 'N' \n"
    )
    ImageFile select(int sellID);
}
