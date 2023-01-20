package com.project.shop.feature.board.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Board {
    private int boardID;
    private String loginID;
    private String title;
    private String content;
    private String writer;
    private Date createDate;
    private Date updateDate;
    private int idx;
}
