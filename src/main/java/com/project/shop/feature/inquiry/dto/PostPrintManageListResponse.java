package com.project.shop.feature.inquiry.dto;

import com.project.shop.feature.inquiry.entity.Inquiry;
import com.project.shop.feature.page.Paging;
import lombok.Data;

import java.util.List;

@Data
public class PostPrintManageListResponse {
    private Paging paging;
    private List<Inquiry> inquiryList;
}
