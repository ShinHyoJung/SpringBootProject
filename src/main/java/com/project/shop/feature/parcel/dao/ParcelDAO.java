package com.project.shop.feature.parcel.dao;

import com.project.shop.feature.parcel.entity.Parcel;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        String sql = "INSERT INTO parcel (name, address, status," +
                "waybill_number, purchase_id, sell_id, idx) VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, parcel.getName(), parcel.getAddress(), parcel.getStatus(), parcel.getWaybillNumber(),
                parcel.getPurchaseID(), parcel.getSellID(), parcel.getIdx());
    }

    public List<Parcel> select(int idx) {
        String sql = "SELECT * FROM parcel WHERE idx = ?";

        List<Parcel> parcelList = jdbcTemplate.query(sql, new Object[]{idx}, new RowMapper<Parcel>() {
            @Override
            public Parcel mapRow(ResultSet rs, int rowNum) throws SQLException {
                Parcel parcel = new Parcel();
                parcel.setParcelID(rs.getInt("parcel_id"));
                parcel.setName(rs.getString("name"));
                parcel.setAddress(rs.getString("address"));
                parcel.setStatus(rs.getInt("status"));
                parcel.setWaybillNumber(rs.getString("waybill_number"));
                parcel.setPurchaseID(rs.getInt("purchase_id"));
                parcel.setProductID(rs.getInt("product_id"));
                parcel.setSellID(rs.getInt("sell_id"));
                parcel.setIdx(rs.getInt("idx"));
                return parcel;
            }
        });
        return parcelList;
    }


}
