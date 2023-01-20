package com.project.shop.feature.board.dto;

import com.project.shop.feature.board.entity.Board;
import lombok.Data;

@Data
public class PostWrite {
    private String loginID;
    private String title;
    private String content;
    private String writer;
    private int idx;

    public Board toEntity() {
        Board board = new Board();
        board.setLoginID(this.loginID);
        board.setTitle(this.title);
        board.setContent(this.content);
        board.setWriter(this.writer);
        board.setIdx(idx);
        return board;
    }
}
