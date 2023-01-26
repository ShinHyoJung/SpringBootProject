package com.project.shop.feature.inquiry.dao;

import com.project.shop.feature.page.Paging;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.project.shop.feature.inquiry.entity.Inquiry;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

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
public class InquiryDAO {
    private final JdbcTemplate jdbcTemplate;

    public InquiryDAO(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void insert(Inquiry inquiry) {
        String sql = "INSERT INTO inquiry (login_id, title, content, writer, create_date, update_date, idx)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql, inquiry.getLoginID(), inquiry.getTitle(), inquiry.getContent(), inquiry.getWriter(),
                Timestamp.valueOf(LocalDateTime.now()), Timestamp.valueOf(LocalDateTime.now()), inquiry.getIdx());
    }

    public List<Inquiry> selectAll(Paging paging) {
        String sql = "SELECT * FROM inquiry LIMIT ?, ?";

        List<Inquiry> inquiryList = jdbcTemplate.query(sql, new Object[]{paging.getSkip(), paging.getCountPerPage()},
                new RowMapper<Inquiry>() {
            @Override
            public Inquiry mapRow(ResultSet rs, int rowNum) throws SQLException {
                Inquiry board = new Inquiry();
                board.setInquiryID(rs.getInt("inquiry_id"));
                board.setTitle(rs.getString("title"));
                board.setWriter(rs.getString("writer"));
                board.setCreateDate(rs.getDate("create_date"));
                board.setUpdateDate(rs.getDate("update_date"));
                return board;
            }
        });
        return inquiryList;
    }

    public Inquiry select(int inquiryID) {
        String sql = "SELECT * FROM inquiry WHERE 1=1 AND inquiry_id = ?";

        Inquiry inquiry = jdbcTemplate.queryForObject(sql, new Object[]{inquiryID}, new RowMapper<Inquiry>() {
            @Override
            public Inquiry mapRow(ResultSet rs, int rowNum) throws SQLException {
                Inquiry inquiry = new Inquiry();
                inquiry.setTitle(rs.getString("title"));
                inquiry.setWriter(rs.getString("writer"));
                inquiry.setContent(rs.getString("content"));
                inquiry.setCreateDate(rs.getDate("create_date"));
                inquiry.setUpdateDate(rs.getDate("update_date"));
                inquiry.setIdx(rs.getInt("idx"));
                return inquiry;
            }
        });
        return inquiry;
    }

    public int count() {
        String sql = "SELECT COUNT(*) FROM inquiry";

        int total = jdbcTemplate.queryForObject(sql, Integer.class);
        return total;
    }

    public void update(Inquiry inquiry) {
        String sql = "UPDATE inquiry SET title = ?," +
                "content = ?, update_date = ?";

        jdbcTemplate.update(sql, inquiry.getTitle(), inquiry.getContent(), Timestamp.valueOf(LocalDateTime.now()));
    }

    public void delete(int inquiryID) {
        String sql = "DELETE FROM board WHERE 1=1 AND inquiry_id = ?";
        jdbcTemplate.update(sql, new Object[]{inquiryID});
    }
}
