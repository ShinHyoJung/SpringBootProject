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
        String sql = "INSERT INTO inquiry (login_id, title, content, writer, create_date, " +
                "update_date, idx, is_answer) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql, inquiry.getLoginID(), inquiry.getTitle(), inquiry.getContent(), inquiry.getWriter(),
                Timestamp.valueOf(LocalDateTime.now()), Timestamp.valueOf(LocalDateTime.now()), inquiry.getIdx(), 0);
    }

    public List<Inquiry> selectAll(Paging paging, String searchOption, String keyword) {
        String sql = "SELECT * FROM inquiry " +
                "WHERE 1=1 AND " + searchOption + " LIKE concat('%', ?, '%') " +
                "ORDER BY is_answer ASC LIMIT ?, ?";

        List<Inquiry> inquiryList = jdbcTemplate.query(sql, new Object[]{keyword, paging.getSkip(), paging.getCountPerPage()},
                new RowMapper<Inquiry>() {
            @Override
            public Inquiry mapRow(ResultSet rs, int rowNum) throws SQLException {
                Inquiry inquiry = new Inquiry();
                inquiry.setInquiryID(rs.getInt("inquiry_id"));
                inquiry.setTitle(rs.getString("title"));
                inquiry.setWriter(rs.getString("writer"));
                inquiry.setCreateDate(rs.getDate("create_date"));
                inquiry.setUpdateDate(rs.getDate("update_date"));
                inquiry.setAnswer(rs.getBoolean("is_answer"));
                return inquiry;
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
                inquiry.setInquiryID(rs.getInt("inquiry_id"));
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

    public int count(String searchOption, String keyword) {
        String sql = "SELECT COUNT(*) FROM inquiry WHERE 1=1 AND " + searchOption + " LIKE concat('%', ?, '%')";

        int total = jdbcTemplate.queryForObject(sql, new Object[]{keyword}, Integer.class);
        return total;
    }

    public void update(Inquiry inquiry) {
        String sql = "UPDATE inquiry SET title = ?," +
                "content = ?, update_date = ?";

        jdbcTemplate.update(sql, inquiry.getTitle(), inquiry.getContent(), Timestamp.valueOf(LocalDateTime.now()));
    }

    public void delete(int inquiryID) {
        String sql = "DELETE FROM inquiry WHERE 1=1 AND inquiry_id = ?";

        jdbcTemplate.update(sql, inquiryID);
    }

    public List<Inquiry> selectAllByIdx(int idx, Paging paging, String searchOption, String keyword) {
        String sql = "SELECT * FROM inquiry WHERE 1=1 AND idx = ? " +
                "AND " + searchOption + " LIKE concat('%', ?, '%')" +
                " ORDER BY is_answer ASC LIMIT ?, ?";

        List<Inquiry> inquiryList = jdbcTemplate.query(sql, new Object[]{idx, keyword, paging.getSkip(), paging.getCountPerPage()}, new RowMapper<Inquiry>() {
            @Override
            public Inquiry mapRow(ResultSet rs, int rowNum) throws SQLException {
                Inquiry inquiry = new Inquiry();
                inquiry.setInquiryID(rs.getInt("inquiry_id"));
                inquiry.setTitle(rs.getString("title"));
                inquiry.setCreateDate(rs.getDate("create_date"));
                inquiry.setUpdateDate(rs.getDate("update_date"));
                inquiry.setAnswer(rs.getBoolean("is_answer"));
                return inquiry;
            }
        });
        return inquiryList;
    }

    public int countByIdx(int idx, String searchOption, String keyword) {
        String sql = "SELECT COUNT(*) FROM inquiry WHERE 1=1 AND idx = ? AND " + searchOption + " LIKE concat('%', ?, '%')";

        int count = jdbcTemplate.queryForObject(sql, new Object[]{idx, keyword}, Integer.class);
        return count;
    }

    public void updateIsAnswer(boolean isAnswer, int inquiryID) {
        String sql = "UPDATE inquiry SET is_answer = ? WHERE 1=1 AND inquiry_id = ?";

        jdbcTemplate.update(sql, isAnswer, inquiryID);
    }
}
