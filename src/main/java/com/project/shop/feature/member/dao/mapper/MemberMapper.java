package com.project.shop.feature.member.dao.mapper;

import com.project.shop.feature.member.entity.Member;
import org.apache.ibatis.annotations.*;

@Mapper
public interface MemberMapper {
    @Insert(
            "INSERT INTO member ( \n" +
                    "member_id, \n" +
                    "password, \n" +
                    "name, \n" +
                    "birth, \n" +
                    "address, \n" +
                    "mobile, \n" +
                    "mail, \n" +
                    "create_date, \n" +
                    "update_date \n" +
                    ") VALUES ( \n" +
                    "#{memberID}, \n" +
                    "#{password}, \n" +
                    "#{name}, \n" +
                    "#{birth}, \n" +
                    "#{address}, \n" +
                    "#{mobile}, \n" +
                    "#{mail}, \n" +
                    "NOW(), \n" +
                    "NOW() \n" +
                    ") \n"

    )
    void insert(Member member);

    @Select(
            "SELECT idx AS idx,\n" +
                    "member_id AS memberID, \n" +
                    "password AS password \n" +
                    "FROM member \n" +
                    "WHERE 1=1 \n" +
                    "AND member_id = #{memberID}"
    )
    Member selectID(String memberID);

    @Select(
            "SELECT idx AS idx, \n" +
                    "member_id AS memberID, \n" +
                    "name AS name, \n" +
                    "birth AS birth, \n" +
                    "mobile AS mobile, \n" +
                    "mail AS mail, \n" +
                    "address AS address, \n" +
                    "create_date AS createDate, \n" +
                    "update_date AS updateDate \n" +
                    "FROM member \n" +
                    "WHERE 1=1 \n" +
                    "AND idx = #{idx}\n"
    )
    Member select(int idx);

    @Update(
            "UPDATE member \n" +
                    "SET password = #{password}, \n" +
                    "name = #{name}, \n" +
                    "birth = #{birth}, \n" +
                    "mobile = #{mobile}, \n" +
                    "mail = #{mail}, \n" +
                    "address = #{address}, \n" +
                    "update_date = now() \n" +
                    "WHERE 1=1 \n" +
                    "AND idx = #{idx}\n"
    )
    void update(Member member);

    @Delete(
            "DELETE FROM member\n" +
                    "WHERE 1=1 \n" +
                    "AND idx = #{idx} \n"
    )
    void delete(int idx);
}
