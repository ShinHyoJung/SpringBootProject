package com.project.shop.feature.sell.dao;

import com.project.shop.feature.page.Paging;
import com.project.shop.feature.sell.entity.Sell;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
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
public class SellDAO {
    private final JdbcTemplate jdbcTemplate;

    public SellDAO(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void insert(Sell sell) {
        String sql = "INSERT INTO sell (name, title, content, price, category, thumbnail_image_name, " +
                "title_image_name, detail_image_name, product_id, create_date, update_date) VALUES (" +
                "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, sell.getName(), sell.getTitle(), sell.getContent(),
        sell.getPrice(), sell.getCategory(), sell.getThumbnailImageName(), sell.getTitleImageName(), sell.getDetailImageName(),
                sell.getProductID(), Timestamp.valueOf(LocalDateTime.now()), Timestamp.valueOf(LocalDateTime.now()));
    }

    public List<Sell> selectAll(Paging paging, String category, String searchOption, String keyword) {
        String sql = "SELECT * FROM sell WHERE 1=1 AND category = ? AND " + searchOption +
                " LIKE concat('%', ?, '%') LIMIT ?, ?";
        List<Sell> sellList = jdbcTemplate.query(sql, new Object[]{category, keyword,
                paging.getSkip(), paging.getCountPerPage()}, new RowMapper<Sell>() {
            @Override
            public Sell mapRow(ResultSet rs, int rowNum) throws SQLException {
                Sell sell = new Sell();
                sell.setSellID(rs.getInt("sell_id"));
                sell.setTitle(rs.getString("title"));
                sell.setPrice(rs.getString("price"));
                sell.setThumbnailImageName(rs.getString("thumbnail_image_name"));
                return sell;
            }
        });
        return sellList;
    }

    public Sell select(int sellID) {
        String sql = "SELECT * FROM sell WHERE 1=1 AND sell_id = ?";

        Sell sell = jdbcTemplate.queryForObject(sql, new Object[]{sellID}, new RowMapper<Sell>() {
            @Override
            public Sell mapRow(ResultSet rs, int rowNum) throws SQLException {
                Sell sell = new Sell();
                sell.setSellID(rs.getInt("sell_id"));
                sell.setName(rs.getString("name"));
                sell.setTitle(rs.getString("title"));
                sell.setContent(rs.getString("content"));
                sell.setPrice(rs.getString("price"));
                sell.setCategory(rs.getString("category"));
                sell.setThumbnailImageName(rs.getString("thumbnail_image_name"));
                sell.setTitleImageName(rs.getString("title_image_name"));
                sell.setDetailImageName(rs.getString("detail_image_name"));
                sell.setProductID(rs.getInt("product_id"));
                sell.setCreateDate(rs.getDate("create_date"));
                sell.setUpdateDate(rs.getDate("update_date"));
                return sell;
            }
        });
        return sell;
    }

    public void delete(int sellID) {
        String sql = "DELETE FROM sell WHERE 1=1 AND sell_id = ?";

        jdbcTemplate.update(sql, new Object[]{sellID});

    }

    public int count(String category, String searchOption, String keyword) {
        String sql = "SELECT COUNT(*) FROM sell WHERE 1=1 AND category = ? AND " +  searchOption + " LIKE concat('%', ?, '%')";

        int total = jdbcTemplate.queryForObject(sql, new Object[]{category, keyword}, Integer.class);
        return total;
    }

    public void update(Sell sell) {
        String sql = "UPDATE sell SET title = ?, name = ?, content = ?, " +
                "price = ?, thumbnail_image_name = ?, title_image_name = ?, " +
                "detail_image_name = ?, update_date = ? WHERE 1=1 AND sell_id = ?";

        jdbcTemplate.update(sql,sell.getTitle(), sell.getName(), sell.getContent(), sell.getPrice(),
                sell.getThumbnailImageName(), sell.getTitleImageName(), sell.getDetailImageName(),
                Timestamp.valueOf(LocalDateTime.now()), sell.getSellID());
    }

    public int selectMaxSellID() {
        String sql = "SELECT MAX(sell_id)" +
                "FROM sell";
        int maxSellID = jdbcTemplate.queryForObject(sql, Integer.class);
        return maxSellID;
    }

    public void deleteByProductID(int productID) {
        String sql = "DELETE FROM sell WHERE 1=1 AND product_id = ?";

        jdbcTemplate.update(sql, productID);
    }
}
