package com.project.shop.feature.board.service;

import com.project.shop.feature.board.entity.Board;

import java.util.List;

public interface BoardService {

    void insert(Board board);

    List<Board> selectAll();

    Board select(int boardID);

    void delete(int boardID);

    void update(Board board);
}
