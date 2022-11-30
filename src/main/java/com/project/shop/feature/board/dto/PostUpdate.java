package com.project.shop.feature.board.dto;

import com.project.shop.feature.board.entity.Board;
import lombok.Data;

@Data
public class PostUpdate {
    private int boardID;
    private String title;
    private String content;

    public Board toEntity() {
        Board board = new Board();
        board.setBoardID(this.boardID);
        board.setTitle(this.title);
        board.setContent(this.content);
        return board;
    }
}
