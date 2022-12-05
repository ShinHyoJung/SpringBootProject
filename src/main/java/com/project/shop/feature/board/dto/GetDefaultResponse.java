package com.project.shop.feature.board.dto;

import com.project.shop.feature.board.entity.Board;
import com.project.shop.feature.page.Paging;
import lombok.Data;

import java.util.List;

@Data
public class GetDefaultResponse {

    private List<Board> boardList;
    private Paging paging;

    public GetDefaultResponse(List<Board> boardList, Paging paging) {
        this.boardList = boardList;
        this.paging = paging;
    }
}
