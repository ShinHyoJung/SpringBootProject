package com.project.shop.feature.inquiry.dto;

import com.project.shop.feature.inquiry.entity.Inquiry;
import lombok.Data;

@Data
public class PostUpdate {
    private int inquiryID;
    private String title;
    private String content;

    public Inquiry toEntity() {
        Inquiry inquiry = new Inquiry();
        inquiry.setInquiryID(this.inquiryID);
        inquiry.setTitle(this.title);
        inquiry.setContent(this.content);
        return inquiry;
    }
}
