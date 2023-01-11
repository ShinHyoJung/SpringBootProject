package com.project.shop.feature.board.dto;

import com.project.shop.feature.board.entity.Board;
import com.project.shop.feature.page.Paging;
import lombok.Data;

import java.util.List;

/**
 * <pre>
 * Created with IntelliJ IDEA.
 * Company: NANDSOFT
 * User: sljh1020
 * Date: 2023-01-11
 * Comments:
 * </pre>
 */
@Data
public class PostPrintListResponse {
    private Paging paging;
    private List<Board> boardList;

    public PostPrintListResponse(Paging paging, List<Board> boardList) {
        this.paging = paging;
        this.boardList = boardList;
    }

}
