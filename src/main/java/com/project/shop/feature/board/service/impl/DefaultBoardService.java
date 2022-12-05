package com.project.shop.feature.board.service.impl;

import com.project.shop.feature.board.dao.mapper.BoardMapper;
import com.project.shop.feature.board.entity.Board;
import com.project.shop.feature.board.service.BoardService;
import com.project.shop.feature.page.Paging;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("BoardService")
@RequiredArgsConstructor
public class DefaultBoardService implements BoardService {
    private final BoardMapper boardMapper;
    @Override
    public void insert(Board board) {
        boardMapper.insert(board);
    }

    @Override
    public List<Board> selectAll(Paging paging) {
        return boardMapper.selectAll(paging);
    }

    @Override
    public Board select(int boardID) {
        return boardMapper.select(boardID);
    }

    @Override
    public void delete(int boardID) {
        boardMapper.delete(boardID);
    }

    @Override
    public void update(Board board) {
        boardMapper.update(board);
    }

    @Override
    public int count() {
        return boardMapper.count();
    }
}
