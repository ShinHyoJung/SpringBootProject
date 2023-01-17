package com.project.shop.feature.cart.dao;

import com.project.shop.feature.cart.entity.Cart;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

/**
 * <pre>
 * Created with IntelliJ IDEA.
 * Company: NANDSOFT
 * User: sljh1020
 * Date: 2023-01-17
 * Comments:
 * </pre>
 */
@Repository
public class CartDAO {
    private final JdbcTemplate jdbcTemplate;

    public CartDAO(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void insert(Cart cart) {
        String sql = "INSERT INTO cart (name, price, quantity, thumbnail_image_name, sell_id) " +
                "VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, cart.getName(), cart.getName(), cart.getQuantity(),
                cart.getThumbnailImageName(), cart.getSellID());
    }
}
