package com.project.shop.feature.board.service;

import com.project.shop.feature.board.entity.Board;
import com.project.shop.feature.page.Paging;

import java.util.List;

public interface BoardService {

    void insert(Board board);

    List<Board> selectAll(Paging paging);

    Board select(int boardID);

    void delete(int boardID);

    void update(Board board);

    int count();
}
