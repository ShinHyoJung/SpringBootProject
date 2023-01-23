package com.project.shop.feature.want.dao;

import com.project.shop.feature.page.Paging;
import com.project.shop.feature.want.entity.Want;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class WantDAO {
    private final JdbcTemplate jdbcTemplate;

    public WantDAO(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void insert(Want want) {
        String sql = "INSERT INTO want(name, price, thumbnail_image_name, sell_id, idx) VALUES (?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql, want.getName(), want.getPrice(), want.getThumbnailImageName(), want.getSellID(), want.getIdx());
    }

    public Want select(int sellID, int idx) {
        String sql = "SELECT * FROM want WHERE 1=1 AND sell_id = ? AND idx = ?";
        Want want = jdbcTemplate.queryForObject(sql, new Object[]{sellID, idx}, new RowMapper<Want>() {
            @Override
            public Want mapRow(ResultSet rs, int rowNum) throws SQLException {
                Want want = new Want();
                want.setWantID(rs.getInt("want_id"));
                want.setSellID(rs.getInt("sell_id"));
                want.setIdx(rs.getInt("idx"));
                return want;
            }
        });
        return want;
    }

    public List<Want> selectAll(int idx, Paging paging) {
        String sql = "SELECT * FROM want WHERE 1=1 AND idx = ? LIMIT ?, ?";

        List<Want> wantList = jdbcTemplate.query(sql, new Object[]{idx, paging.getSkip(), paging.getCountPerPage()}, new RowMapper<Want>() {
            @Override
            public Want mapRow(ResultSet rs, int rowNum) throws SQLException {
                Want want = new Want();
                want.setWantID(rs.getInt("want_id"));
                want.setName(rs.getString("name"));
                want.setPrice(rs.getString("price"));
                want.setThumbnailImageName(rs.getString("thumbnail_image_name"));
                want.setSellID(rs.getInt("sell_id"));
                return want;
            }
        });
        return wantList;
    }

    public void delete(int sellID, int idx) {
        String sql = "DELETE FROM want WHERE 1=1 AND sell_id = ? AND idx = ?";
        jdbcTemplate.update(sql, sellID, idx);
    }

    public int count(int idx) {
        String sql = "SELECT COUNT(*) FROM want WHERE 1=1 AND idx = ?";
        int total = jdbcTemplate.queryForObject(sql, new Object[]{idx}, Integer.class);
        return total;
    }
}
