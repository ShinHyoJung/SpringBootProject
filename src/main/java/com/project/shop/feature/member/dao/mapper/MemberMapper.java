package com.project.shop.feature.member.dao.mapper;

import com.project.shop.feature.member.entity.Member;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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
                    ")"

    )
    void insert(Member member);

    @Select(
            "SELECT member_id AS memberID, \n" +
                    "password AS password \n" +
                    "FROM member \n" +
                    "WHERE 1=1 \n" +
                    "AND member_id = #{memberID}"
    )
    Member select(String memberID);
}
