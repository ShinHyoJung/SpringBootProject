package com.project.shop.feature.inquiry.dto;

import com.project.shop.feature.inquiry.entity.Inquiry;
import lombok.Data;

@Data
public class PostWrite {
    private String loginID;
    private String title;
    private String content;
    private String writer;
    private int idx;

    public Inquiry toEntity() {
        Inquiry inquiry = new Inquiry();
        inquiry.setLoginID(this.loginID);
        inquiry.setTitle(this.title);
        inquiry.setContent(this.content);
        inquiry.setWriter(this.writer);
        inquiry.setIdx(idx);
        return inquiry;
    }
}
