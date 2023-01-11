package com.project.shop.feature.board.service.impl;

import com.project.shop.feature.board.dao.BoardDAO;
import com.project.shop.feature.board.entity.Board;
import com.project.shop.feature.board.service.BoardService;
import com.project.shop.feature.page.Paging;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("BoardService")
@RequiredArgsConstructor
public class DefaultBoardService implements BoardService {
    private final BoardDAO boardDAO;
    @Override
    public void insert(Board board) {
        boardDAO.insert(board);
    }

    @Override
    public List<Board> selectAll(Paging paging) {
        return boardDAO.selectAll(paging);
    }

    @Override
    public Board select(int boardID) {
        return boardDAO.select(boardID);
    }

    @Override
    public void delete(int boardID) {
        boardDAO.delete(boardID);
    }

    @Override
    public void update(Board board) {
        boardDAO.update(board);
    }

    @Override
    public int count() {
        return boardDAO.count();
    }
}
