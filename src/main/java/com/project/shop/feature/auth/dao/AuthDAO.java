package com.project.shop.feature.auth.dao;

import com.project.shop.feature.member.entity.Member;
import com.project.shop.feature.util.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Repository
public class AuthDAO {

    private final JdbcTemplate jdbcTemplate;

    public AuthDAO(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void insert(Member member) {
        String sql = "INSERT INTO auth (username, auth, auth_date) " +
                "VALUES(?, ?, ?)";
        Collection<GrantedAuthority> authorities = member.getAuthorities();

        if (StringUtils.isNotEmpty(member.getUsername())) {
            for (GrantedAuthority authority : authorities) {
                jdbcTemplate.update(sql, member.getUsername(), authority.getAuthority(), Timestamp.valueOf(LocalDateTime.now()));
            }
        }
    }

    public Collection<GrantedAuthority> select(String username) {
        String sql = "SELECT auth FROM auth WHERE 1=1 AND username = ?";
        Collection<GrantedAuthority> authorities = new ArrayList<>();

        SimpleGrantedAuthority authority = jdbcTemplate.queryForObject(sql, new Object[]{username}, SimpleGrantedAuthority.class);

        authorities.add(authority);
        return authorities;
    }

    public void update(String username, String auth) {
        String sql = "UPDATE auth SET auth = ? WHERE 1=1 AND username = ?";
        jdbcTemplate.update(sql, username, auth);
    }

    public void delete(String username) {
        String sql = "DELETE FROM auth WHERE 1=1 AND username = ?";
        jdbcTemplate.update(sql, username);
    }
}
