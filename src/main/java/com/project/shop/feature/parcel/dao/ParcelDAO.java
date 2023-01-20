package com.project.shop.feature.parcel.dao;

import com.project.shop.feature.parcel.entity.Parcel;
import com.project.shop.feature.page.Paging;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <pre>
 * Created with IntelliJ IDEA.
 * Company: NANDSOFT
 * User: sljh1020
 * Date: 2023-01-18
 * Comments:
 * </pre>
 */
@Repository
public class ParcelDAO {
    private final JdbcTemplate jdbcTemplate;

    public ParcelDAO(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void insert(Parcel parcel) {
        String sql = "INSERT INTO parcel (name, address, detail_address, zip_code, quantity, status, " +
                "waybill_number, purchase_id, sell_id, idx, purchase_date, ship_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, parcel.getName(), parcel.getAddress(), parcel.getDetailAddress(), parcel.getZipCode(),
                parcel.getQuantity(), parcel.getStatus(), parcel.getWaybillNumber(),
                parcel.getPurchaseID(), parcel.getSellID(), parcel.getIdx(), parcel.getPurchaseDate(), Timestamp.valueOf(LocalDateTime.now()));
    }

    public List<Parcel> select(int idx, Paging paging) {
        String sql = "SELECT * FROM parcel WHERE idx = ? LIMIT ?, ?";

        List<Parcel> parcelList = jdbcTemplate.query(sql, new Object[]{idx, paging.getSkip(), paging.getCountPerPage()}, new RowMapper<Parcel>() {
            @Override
            public Parcel mapRow(ResultSet rs, int rowNum) throws SQLException {
                Parcel parcel = new Parcel();
                parcel.setParcelID(rs.getInt("parcel_id"));
                parcel.setName(rs.getString("name"));
                parcel.setAddress(rs.getString("address"));
                parcel.setDetailAddress(rs.getString("detail_address"));
                parcel.setZipCode(rs.getString("zip_code"));
                parcel.setQuantity(rs.getInt("quantity"));
                parcel.setStatus(rs.getInt("status"));
                parcel.setWaybillNumber(rs.getString("waybill_number"));
                parcel.setPurchaseID(rs.getInt("purchase_id"));
                parcel.setProductID(rs.getInt("product_id"));
                parcel.setSellID(rs.getInt("sell_id"));
                parcel.setIdx(rs.getInt("idx"));
                parcel.setPurchaseDate(rs.getDate("purchase_date"));
                parcel.setShipDate(rs.getDate("ship_date"));
                return parcel;
            }
        });
        return parcelList;
    }

    public void deleteByPurchaseID(int purchaseID) {
        String sql = "DELETE FROM parcel WHERE 1=1 AND purchase_id = ?";

        jdbcTemplate.update(sql, purchaseID);
    }

    public void deleteByParcelID(int parcelID) {
        String sql = "DELETE FROM parcel WHERE 1=1 AND parcel_id = ?";

        jdbcTemplate.update(sql, parcelID);
    }

    public void updateStatus(int status, int parcelID) {
        String sql = "UPDATE parcel SET status = ? WHERE 1=1 AND parcel_id = ?";
        jdbcTemplate.update(sql, status, parcelID);
    }

    public int count(int idx) {
        String sql = "SELECT COUNT(*) FROM parcel WHERE 1=1 AND idx = ?";
        int total = jdbcTemplate.queryForObject(sql, new Object[]{idx}, Integer.class);
        return total;
    }
}
