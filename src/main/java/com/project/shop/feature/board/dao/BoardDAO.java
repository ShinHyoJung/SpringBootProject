package com.project.shop.feature.board.dao;

import com.project.shop.feature.page.Paging;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.project.shop.feature.board.entity.Board;
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
public class BoardDAO {
    private final JdbcTemplate jdbcTemplate;

    public BoardDAO(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void insert(Board board) {
        String sql = "INSERT INTO board (member_id, title, content, writer, create_date, update_date, idx)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        Date now = new Date();
        long nowDate = now.getDate();

        jdbcTemplate.update(sql, board.getMemberID(), board.getTitle(), board.getContent(), board.getWriter(),
                new java.sql.Date(nowDate), Timestamp.valueOf(LocalDateTime.now()), board.getIdx());
    }

    public List<Board> selectAll(Paging paging) {
        String sql = "SELECT * FROM board LIMIT ?, ?";

        List<Board> boardList = jdbcTemplate.query(sql, new Object[]{paging.getSkip(), paging.getCountPerPage()},
                new RowMapper<Board>() {
            @Override
            public Board mapRow(ResultSet rs, int rowNum) throws SQLException {
                Board board = new Board();
                board.setBoardID(rs.getInt("board_id"));
                board.setTitle(rs.getString("title"));
                board.setWriter(rs.getString("writer"));
                board.setCreateDate(rs.getDate("create_date"));
                board.setUpdateDate(rs.getDate("update_date"));
                return board;
            }
        });
        return boardList;
    }

    public Board select(int boardID) {
        String sql = "SELECT * FROM board WHERE 1=1 AND board_id = ?";

        Board board = jdbcTemplate.queryForObject(sql, new Object[]{boardID}, new RowMapper<Board>() {
            @Override
            public Board mapRow(ResultSet rs, int rowNum) throws SQLException {
                Board board = new Board();
                board.setTitle(rs.getString("title"));
                board.setWriter(rs.getString("writer"));
                board.setContent(rs.getString("content"));
                board.setCreateDate(rs.getDate("create_date"));
                board.setUpdateDate(rs.getDate("update_date"));
                board.setIdx(rs.getInt("idx"));
                board.setMemberID(rs.getString("member_id"));
                return board;
            }
        });
        return board;
    }

    public int count() {
        String sql = "SELECT COUNT(*) FROM board";

        int total = jdbcTemplate.queryForObject(sql, Integer.class);
        return total;
    }

    public void update(Board board) {
        String sql = "UPDATE board SET title = ?," +
                "content = ?, update_date = ?";

        jdbcTemplate.update(sql, board.getTitle(), board.getContent(), Timestamp.valueOf(LocalDateTime.now()));
    }

    public void delete(int boardID) {
        String sql = "DELETE FROM board WHERE 1=1 AND board_id = ?";
        jdbcTemplate.update(sql, new Object[]{boardID});
    }
}
