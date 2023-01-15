package com.project.shop.feature.purchase.dao;

import com.project.shop.feature.purchase.entity.Purchase;
import org.springframework.jdbc.core.ArgumentPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class PurchaseDAO {
    private final JdbcTemplate jdbcTemplate;

    public PurchaseDAO(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void insert(Purchase purchase) {
        String sql = "INSERT INTO purchase (name, price, address, sell_id, idx, delivery_status, purchase_date)" +
                " VALUES (?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql, purchase.getName(), purchase.getPrice(), purchase.getAddress(), purchase.getSellID(), purchase.getIdx(),
                purchase.getDeliveryStatus(), Timestamp.valueOf(LocalDateTime.now()));
    }

    public List<Purchase> select(int idx) {
        String sql = "SELECT * FROM purchase WHERE idx = ?";

        List<Purchase> purchaseList = jdbcTemplate.query(sql, new Object[]{idx}, new RowMapper<Purchase>() {
            @Override
            public Purchase mapRow(ResultSet rs, int rowNum) throws SQLException {
                Purchase purchase = new Purchase();
                purchase.setPurchaseID(rs.getInt("purchase_id"));
                purchase.setName(rs.getString("name"));
                purchase.setPrice(rs.getString("price"));
                purchase.setAddress(rs.getString("address"));
                purchase.setProductID(rs.getInt("product_id"));
                purchase.setSellID(rs.getInt("sell_id"));
                purchase.setIdx(rs.getInt("idx"));
                purchase.setDeliveryStatus(rs.getString("delivery_status"));
                purchase.setPurchaseDate(rs.getDate("purchase_date"));
                return purchase;
            }
        });
        return purchaseList;
    }
}
