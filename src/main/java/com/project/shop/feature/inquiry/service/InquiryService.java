package com.project.shop.feature.inquiry.service;

import com.project.shop.feature.inquiry.entity.Inquiry;
import com.project.shop.feature.page.Paging;

import java.util.List;

public interface InquiryService {

    void insert(Inquiry inquiry);

    List<Inquiry> selectAll(Paging paging);

    Inquiry select(int inquiryID);

    void delete(int inquiryID);

    void update(Inquiry inquiry);

    int count();
}
