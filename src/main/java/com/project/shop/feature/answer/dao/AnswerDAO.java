package com.project.shop.feature.answer.dao;

import com.project.shop.feature.answer.entity.Answer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Repository
public class AnswerDAO {

    private final JdbcTemplate jdbcTemplate;

    public AnswerDAO(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void insert(Answer answer) {
        String sql = "INSERT INTO answer(writer, content, create_date, update_date, inquiry_id, idx) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, answer.getWriter(), answer.getContent(), Timestamp.valueOf(LocalDateTime.now()),
                Timestamp.valueOf(LocalDateTime.now()), answer.getInquiryID(), answer.getIdx());
    }

    public void delete(int answerID) {
        String sql = "DELETE FROM answer WHERE 1=1 AND answer_id = ?";
        jdbcTemplate.update(sql, answerID);
    }

    public Answer select(int inquiryID) {
        String sql = "SELECT * FROM answer WHERE 1=1 AND inquiry_id = ?";

        Answer answer = jdbcTemplate.queryForObject(sql, new Object[]{inquiryID}, new RowMapper<Answer>() {
            @Override
            public Answer mapRow(ResultSet rs, int rowNum) throws SQLException {
                Answer answer = new Answer();
                answer.setAnswerID(rs.getInt("answer_id"));
                answer.setWriter(rs.getString("writer"));
                answer.setContent(rs.getString("content"));
                answer.setCreateDate(rs.getDate("create_date"));
                answer.setUpdateDate(rs.getDate("update_date"));
                answer.setInquiryID(rs.getInt("inquiry_id"));
                answer.setIdx(rs.getInt("idx"));
                return answer;
            }
        });
        return answer;
    }

    public void update(Answer answer) {
        String sql = "UPDATE answer SET content = ?, update_date = ? WHERE 1=1 AND answer_id = ?";
        jdbcTemplate.update(sql, answer.getContent(), Timestamp.valueOf(LocalDateTime.now()), answer.getAnswerID());
    }
}
