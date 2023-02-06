package com.project.shop.feature.answer.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Answer {
    private int answerID;
    private String writer;
    private String content;
    private Date createDate;
    private Date updateDate;
    private int inquiryID;
    private int idx;
}
