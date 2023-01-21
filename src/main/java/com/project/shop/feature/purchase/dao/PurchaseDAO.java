package com.project.shop.feature.purchase.dao;

import com.project.shop.feature.page.Paging;
import com.project.shop.feature.purchase.entity.Purchase;
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

    public void insert(List<Purchase> purchaseList) {
        String sql = "INSERT INTO purchase (name, price, quantity, address, detail_address, zip_code, thumbnail_image_name, imp_uid, " +
                "sell_id, product_id, idx, order_status, purchase_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        for(Purchase purchase : purchaseList) {
            jdbcTemplate.update(sql, purchase.getName(), purchase.getPrice(), purchase.getQuantity(), purchase.getAddress(),
                    purchase.getDetailAddress(), purchase.getZipCode(), purchase.getThumbnailImageName(), purchase.getImpUid(),
                    purchase.getSellID(), purchase.getProductID(), purchase.getIdx(), purchase.getOrderStatus(), Timestamp.valueOf(LocalDateTime.now()));
        }
    }

    public List<Purchase> selectByIdx(int idx, Paging paging) {
        String sql = "SELECT * FROM purchase WHERE idx = ? LIMIT ?, ?";

        List<Purchase> purchaseList = jdbcTemplate.query(sql, new Object[]{idx, paging.getSkip(), paging.getCountPerPage()}, new RowMapper<Purchase>() {
            @Override
            public Purchase mapRow(ResultSet rs, int rowNum) throws SQLException {
                Purchase purchase = new Purchase();
                purchase.setPurchaseID(rs.getInt("purchase_id"));
                purchase.setName(rs.getString("name"));
                purchase.setPrice(rs.getString("price"));
                purchase.setQuantity(rs.getInt("quantity"));
                purchase.setAddress(rs.getString("address"));
                purchase.setDetailAddress(rs.getString("detail_address"));
                purchase.setZipCode(rs.getString("zip_code"));
                purchase.setThumbnailImageName(rs.getString("thumbnail_image_name"));
                purchase.setImpUid(rs.getString("imp_uid"));
                purchase.setProductID(rs.getInt("product_id"));
                purchase.setSellID(rs.getInt("sell_id"));
                purchase.setProductID(rs.getInt("product_id"));
                purchase.setIdx(rs.getInt("idx"));
                purchase.setOrderStatus(rs.getString("order_status"));
                purchase.setPurchaseDate(rs.getDate("purchase_date"));
                return purchase;
            }
        });
        return purchaseList;
    }

    public void delete(int purchaseID) {
        String sql = "DELETE FROM purchase WHERE 1=1 AND purchase_id = ?";

        jdbcTemplate.update(sql, purchaseID);
    }

    public Purchase selectByPurchaseID (int purchaseID) {
        String sql = "SELECT * FROM purchase WHERE purchase_id = ?";

        Purchase purchase = jdbcTemplate.queryForObject(sql, new Object[]{purchaseID}, new RowMapper<Purchase>() {
            @Override
            public Purchase mapRow(ResultSet rs, int rowNum) throws SQLException {
                Purchase purchase = new Purchase();
                purchase.setPurchaseID(rs.getInt("purchase_id"));
                purchase.setName(rs.getString("name"));
                purchase.setPrice(rs.getString("price"));
                purchase.setAddress(rs.getString("address"));
                purchase.setDetailAddress(rs.getString("detail_address"));
                purchase.setZipCode(rs.getString("zip_code"));
                purchase.setThumbnailImageName(rs.getString("thumbnail_image_name"));
                purchase.setImpUid(rs.getString("imp_uid"));
                purchase.setProductID(rs.getInt("product_id"));
                purchase.setSellID(rs.getInt("sell_id"));
                purchase.setIdx(rs.getInt("idx"));
                purchase.setOrderStatus(rs.getString("order_status"));
                purchase.setPurchaseDate(rs.getDate("purchase_date"));
                return purchase;
            }
        });
        return purchase;
    }

    public int selectMaxPurchaseID() {
        String sql = "SELECT MAX(purchase_id)" +
                "FROM purchase";
        int maxSellID = jdbcTemplate.queryForObject(sql, Integer.class);
        return maxSellID;
    }

    public void updateOrderStatus(String orderStatus, int purchaseID) {
        String sql = "UPDATE purchase SET order_status = ? WHERE 1=1 AND purchase_id = ?";
        jdbcTemplate.update(sql, orderStatus, purchaseID);
    }

    public int count(int idx) {
        String sql = "SELECT COUNT(*) FROM purchase WHERE 1=1 AND idx = ?";
        int total = jdbcTemplate.queryForObject(sql, new Object[]{idx}, Integer.class);
        return total;
    }
}
