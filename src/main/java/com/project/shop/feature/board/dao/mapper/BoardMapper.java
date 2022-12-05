package com.project.shop.feature.board.dao.mapper;

import com.project.shop.feature.board.entity.Board;
import com.project.shop.feature.page.Paging;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BoardMapper {
    @Insert(
            "INSERT INTO board ( \n" +
                    "member_id, \n" +
                    "title, \n" +
                    "content, \n" +
                    "writer, \n" +
                    "create_date, \n" +
                    "update_date, \n" +
                    "idx \n" +
                    ") VALUES ( \n" +
                    "#{memberID}, \n" +
                    "#{title}, \n" +
                    "#{content}, \n" +
                    "#{writer}, \n" +
                    "NOW(), \n" +
                    "NOW(), \n" +
                    "#{idx} \n" +
                    ") \n"
    )
    void insert(Board board);

    @Select(
            "SELECT  board_id AS boardID, \n" +
                    "title AS title, \n" +
                    "writer AS writer, \n" +
                    "create_date AS createDate, \n" +
                    "update_date AS updateDate \n" +
                    "FROM board \n" +
                    "LIMIT #{skip}, #{amount} \n"
    )
    List<Board> selectAll(Paging paging);

    @Select(
            "SELECT board_id AS boardID, \n" +
                    "title AS title, \n" +
                    "content AS content, \n" +
                    "writer AS writer, \n" +
                    "create_date AS createDate, \n" +
                    "update_date AS updateDate \n" +
                    "FROM board \n" +
                    "WHERE 1=1 \n" +
                    "AND board_id = #{boardID}\n"
    )
    Board select(int boardID);

    @Delete(
            "DELETE FROM \n" +
                    "board \n" +
                    "WHERE 1=1 \n" +
                    "AND board_id = #{boardID} \n"
    )
    void delete(int boardID);

    @Update(
            "UPDATE board SET \n" +
                    "title = #{title}, \n" +
                    "content = #{content}, \n" +
                    "update_date = NOW() \n" +
                    "WHERE 1=1 \n" +
                    "AND board_id = #{boardID}"
    )
    void update(Board board);

    @Select(
            "SELECT COUNT(*) \n" +
                    "FROM board \n"
    )
    int count();
}
