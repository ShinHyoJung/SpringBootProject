package com.project.shop.feature.board.dto;

import lombok.Data;

@Data
public class GetUpdateResponse {
    private int boardID;
    private String title;
    private String writer;
    private String content;
}
