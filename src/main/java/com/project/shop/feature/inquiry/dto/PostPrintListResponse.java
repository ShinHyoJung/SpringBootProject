package com.project.shop.feature.inquiry.dto;

import com.project.shop.feature.inquiry.entity.Inquiry;
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
    private List<Inquiry> inquiryList;

    public PostPrintListResponse(Paging paging, List<Inquiry> inquiryList) {
        this.paging = paging;
        this.inquiryList = inquiryList;
    }

}
