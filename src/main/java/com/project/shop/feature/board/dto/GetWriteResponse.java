package com.project.shop.feature.board.dto;

import lombok.Data;

@Data
public class GetWriteResponse {
    private String loginID;
    private String writer;
    private int idx;
}
