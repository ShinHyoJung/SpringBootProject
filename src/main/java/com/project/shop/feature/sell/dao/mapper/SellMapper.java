package com.project.shop.feature.sell.dao.mapper;

import com.project.shop.feature.page.Paging;
import com.project.shop.feature.sell.entity.Sell;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SellMapper {

    @Insert (
            "INSERT INTO sell ( \n" +
                    "name, \n" +
                    "title, \n" +
                    "content, \n" +
                    "product_code, \n" +
                    "price, \n" +
                    "thumbnail_image, " +
                    "thumbnail_image_path, \n" +
                    "detail_image, \n" +
                    "detail_image_path, \n" +
                    "create_date, \n" +
                    "update_date \n" +
                    ") VALUES ( \n" +
                    "#{name}, \n" +
                    "#{title}, \n" +
                    "#{content}, \n" +
                    "#{productCode}, \n" +
                    "#{price}, \n" +
                    "#{thumbnailImage}, \n" +
                    "#{thumbnailImagePath}, \n" +
                    "#{detailImage}, \n" +
                    "#{detailImagePath}, \n" +
                    "NOW(), \n" +
                    "NOW() \n" +
                    ") \n"
    )
    void insert(Sell sell);

    @Select(
            "SELECT sell_id AS sellID, \n" +
                    "name AS name, \n" +
                    "title AS title, \n" +
                    "price AS price, \n" +
                    "thumbnail_image AS thumbnailImage, \n" +
                    "thumbnail_image_path AS thumbnailImagePath, \n" +
                    "create_date AS createDate, \n" +
                    "update_date AS updateDate \n" +
                    "FROM sell \n" +
                    "LIMIT #{skip}, #{amount}\n"
    )
    List<Sell> selectAll(Paging paging);

    @Select(
            "SELECT sell_id AS sellID, \n" +
                    "name AS name,  \n" +
                    "title AS title,  \n" +
                    "content AS content,  \n" +
                    "product_code AS productCode,  \n" +
                    "price AS price,  \n" +
                    "detail_image AS detailImage, \n" +
                    "detail_image_path AS detailImagePath, \n" +
                    "create_date AS createDate,  \n" +
                    "update_date AS updateDate  \n" +
                    "FROM sell  \n" +
                    "WHERE 1=1  \n" +
                    "AND sell_id = #{sellID} \n"
    )
    Sell select(int sellID);

    @Delete(
            "DELETE FROM \n" +
                    "sell \n" +
                    "WHERE 1=1 \n" +
                    "AND sell_id = #{sellID} \n"
    )
    void delete(int sellID);

    @Select(
            "SELECT COUNT(*) \n" +
                    "FROM sell \n"
    )
    int count();

    @Update("UPDATE sell SET \n" +
            "title = #{title}, \n" +
            "content = #{content}, \n" +
            "price = #{price}, \n" +
            "product_code = #{productCode}, \n" +
            "detail_image = #{detailImage}, \n" +
            "detail_image_path = #{detailImagePath}, \n" +
            "thumbnail_image = #{thumbnailImage}, \n" +
            "thumbnail_image_path = #{thumbnailImagePath}, \n" +
            "update_date = NOW() \n" +
            "WHERE 1=1 \n" +
            "AND sell_id = #{sellID} \n"
    )
    void update(Sell sell);
}
