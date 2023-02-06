package com.project.shop.feature.answer.dto;

import com.project.shop.feature.answer.entity.Answer;
import lombok.Data;

@Data
public class PostWrite {
    private int idx;
    private int inquiryID;
    private String content;

    public Answer toEntity(String writer) {
        Answer answer = new Answer();
        answer.setWriter(writer);
        answer.setContent(this.content);
        answer.setInquiryID(this.inquiryID);
        answer.setIdx(this.idx);
        return answer;
    }
}
