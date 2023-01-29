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
                "login_id, password, name, birth, address, detail_address, zip_code, phone, email, create_date, update_date) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql, member.getLoginID(), member.getPassword(), member.getName(), member.getBirth(),
                member.getAddress(), member.getDetailAddress(), member.getZipCode(), member.getPhone(), member.getEmail(),
                Timestamp.valueOf(LocalDateTime.now()), Timestamp.valueOf(LocalDateTime.now()));
    }

    public Member selectByLoginID(String loginID) {
        String sql = "SELECT idx AS idx, password AS password" +
                "name AS name  \n" +
                "FROM member WHERE 1=1 AND login_id = ?";

        Member member = jdbcTemplate.queryForObject(sql, new Object[]{loginID}, new RowMapper<Member>() {
            @Override
            public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
                Member member = new Member();
                member.setIdx(rs.getInt("idx"));
                member.setPassword(rs.getString("password"));
                member.setAuth(rs.getString("auth"));
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
                member.setIdx(rs.getInt("idx"));
                member.setLoginID(rs.getString("login_id"));
                member.setPassword(rs.getString("password"));
                member.setName(rs.getString("name"));
                member.setPhone(rs.getString("phone"));
                member.setEmail(rs.getString("email"));
                member.setBirth(rs.getString("birth"));
                member.setAddress(rs.getString("address"));
                member.setDetailAddress(rs.getString("detail_address"));
                member.setZipCode(rs.getString("zip_code"));
                member.setCreateDate(rs.getDate("create_date"));
                member.setUpdateDate(rs.getDate("update_date"));
                return member;
            }
        });
        return member;
    }

    public void update(Member member) {
        String sql = "UPDATE member SET " +
                "password = ?, name = ?, birth = ?, phone = ?, " +
                "email = ?, address = ?, detail_address = ?, zip_code = ?, update_date = ? WHERE 1=1 " +
                "AND idx = ?";

         jdbcTemplate.update(sql, member.getPassword(), member.getName(), member.getBirth(), member.getPhone(),
                member.getEmail(), member.getAddress(), member.getDetailAddress(), member.getZipCode(),
                Timestamp.valueOf(LocalDateTime.now()), member.getIdx());
    }

    public void delete(int idx) {
        String sql = "DELETE FROM member WHERE 1=1 AND idx = ?";
        jdbcTemplate.update(sql, idx);
    }

    public Member selectByNameAndBirth(String name, String birth) {
        String sql = "SELECT * FROM member WHERE 1=1 AND name = ? AND birth = ?";

        Member member = jdbcTemplate.queryForObject(sql, new Object[]{name, birth}, new RowMapper<Member>() {
            @Override
            public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
                Member member = new Member();
                member.setIdx(rs.getInt("idx"));
                member.setEmail(rs.getString("email"));
                return member;
            }
        });
        return member;
    }

    public boolean isValidateLoginID(String loginID) {
        String sql = "SELECT 1 FROM member WHERE 1=1 AND login_id = ?";

        boolean isValidate = jdbcTemplate.queryForObject(sql, new Object[]{loginID}, Boolean.class);
        return isValidate;
    }

    public void updatePassword(String password, int idx) {
        String sql = "UPDATE member SET password = ? WHERE 1=1 AND idx = ?";

        jdbcTemplate.update(sql, password, idx);
    }
}
