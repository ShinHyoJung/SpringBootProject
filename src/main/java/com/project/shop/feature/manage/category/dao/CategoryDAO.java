package com.project.shop.feature.manage.category.dao;

import com.project.shop.feature.manage.category.entity.Category;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CategoryDAO {
    private final JdbcTemplate jdbcTemplate;

    public CategoryDAO(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void insert(Category category) {
        String sql = "INSERT INTO category(name, code) VALUES (?, ?)";

        jdbcTemplate.update(sql, category.getName(), category.getCode());
    }

    public List<Category> selectAll() {
        String sql = "SELECT * FROM category";

        List<Category> categoryList = jdbcTemplate.query(sql, new RowMapper<Category>() {
            @Override
            public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
                Category category = new Category();
                category.setCategoryID(rs.getInt("category_id"));
                category.setName(rs.getString("name"));
                category.setCode(rs.getString("code"));
                return category;
            }
        });
        return categoryList;
    }

    public Category select(int categoryID) {
        String sql = "SELECT * FROM category WHERE 1=1 AND category_id = ?";

        Category category = jdbcTemplate.queryForObject(sql, new Object[]{categoryID}, new RowMapper<Category>() {
            @Override
            public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
                Category category = new Category();
                category.setCategoryID(rs.getInt("category_id"));
                category.setName(rs.getString("name"));
                category.setCode(rs.getString("code"));
                return category;
            }
        });
        return category;
    }

    public void update(Category category) {
        String sql = "UPDATE category SET name = ?, code = ? WHERE 1=1 AND category_id = ?";

        jdbcTemplate.update(sql, category.getName(), category.getCode(), category.getCategoryID());
    }

    public void delete(int categoryID) {
        String sql = "DELETE FROM category WHERE 1=1 AND category_id = ?";

        jdbcTemplate.update(sql, categoryID);
    }

    public String convertCodeToName(String code) {
        String sql = "SELECT name FROM category WHERE 1=1 AND  code = ?";

        String name = jdbcTemplate.queryForObject(sql, new Object[]{code}, String.class);
        return name;
    }

    public String convertNameToCode(String name) {
        String sql = "SELECT code FROM category WHERE 1=1 AND name = ?";
        String code = jdbcTemplate.queryForObject(sql, new Object[]{name}, String.class);
        return code;
    }
}
