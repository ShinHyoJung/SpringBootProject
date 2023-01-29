package com.project.shop.feature.auth.dao;

import com.project.shop.feature.auth.entity.Auth;
import org.springframework.cglib.core.Local;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class AuthDAO {

    private final JdbcTemplate jdbcTemplate;

    public AuthDAO(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void insert(Auth auth) {
        String sql = "INSERT INTO auth (idx, username, password, auth, auth_date)" +
                "VALUES(?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, auth.getIdx(), auth.getUsername(), auth.getPassword(), auth.getAuth(), Timestamp.valueOf(LocalDateTime.now()));
    }

    public List<GrantedAuthority> selectList(String username) {
        String sql = "SELECT * FROM auth WHERE 1=1 AND username = ?";
        List<GrantedAuthority> grantedAuthorityList = jdbcTemplate.queryForList(sql, new Object[]{username}, GrantedAuthority.class);
        return grantedAuthorityList;
    }

    public void update(String username, String auth) {
        String sql = "UPDATE auth SET auth = ? WHERE 1=1 AND username = ?";
        jdbcTemplate.update(sql, username, auth);
    }

}
