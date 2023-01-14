package com.project.shop.feature.member.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.project.shop.feature.member.entity.Member;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 * Created with IntelliJ IDEA.
 * Company: NANDSOFT
 * User: sljh1020
 * Date: 2023-01-09
 * Comments:
 * </pre>
 */
@Repository
public class MemberDAO {
    private final JdbcTemplate jdbcTemplate;

    public MemberDAO(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void insert(Member member) {
        String sql = "INSERT INTO member(" +
                "member_id, password, name, birth, address, mobile, mail, create_date, update_date) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql, member.getMemberID(), member.getPassword(), member.getName(),
                member.getBirth(), member.getAddress(), member.getMobile(), Timestamp.valueOf(LocalDateTime.now()), Timestamp.valueOf(LocalDateTime.now()));
    }

    public Member selectByMemberID(String memberID) {
        String sql = "SELECT idx AS idx, password AS password \n" +
                "FROM member WHERE 1=1 AND member_id = ?";

        Member member = jdbcTemplate.queryForObject(sql, new Object[]{memberID}, new RowMapper<Member>() {
            @Override
            public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
                Member member = new Member();
                member.setIdx(rs.getInt("idx"));
                member.setPassword(rs.getString("password"));
                return member;
            }
        });
        return member;
    }

    public Member select(int idx) {
        String sql = "SELECT * FROM member WHERE 1=1 AND idx = ?";
        Member member = jdbcTemplate.queryForObject(sql, new Object[]{idx}, new RowMapper<Member>() {
            @Override
            public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
                Member member = new Member();
                member.setMemberID(rs.getString("member_id"));
                member.setPassword(rs.getString("password"));
                member.setName(rs.getString("name"));
                member.setMobile(rs.getString("mobile"));
                member.setMail(rs.getString("mail"));
                member.setBirth(rs.getString("birth"));
                member.setAddress(rs.getString("address"));
                member.setCreateDate(rs.getDate("create_date"));
                member.setUpdateDate(rs.getDate("update_date"));
                return member;
            }
        });
        return member;
    }

    public void update(Member member) {
        String sql = "UPDATE member SET " +
                "password = ?, name = ?, birth = ?, mobile = ?" +
                "mail = ?, address = ?, update_date = ? WHERE 1=1" +
                "AND idx = ?";

        jdbcTemplate.update(sql, member.getPassword(), member.getName(), member.getBirth(),
                member.getMail(), member.getAddress(), Timestamp.valueOf(LocalDateTime.now()));
    }

    public void delete(int idx) {
        String sql = "DELETE FROM member WHERE 1=1 AND idx = ?";
        jdbcTemplate.update(sql, new Object[]{idx});
    }

}
