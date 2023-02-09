package com.project.shop.feature.inquiry.service;

import com.project.shop.feature.inquiry.entity.Inquiry;
import com.project.shop.feature.page.Paging;

import java.util.List;

public interface InquiryService {

    void insert(Inquiry inquiry);

    List<Inquiry> selectAll(Paging paging, String searchOption, String keyword);

    Inquiry select(int inquiryID);

    void delete(int inquiryID);

    void update(Inquiry inquiry);

    int count(String searchOption, String keyword);

    List<Inquiry> selectAllByIdx(int idx, Paging paging, String searchOption, String keyword);

    int countByIdx(int idx, String searchOption, String keyword);

    void updateIsAnswer(boolean isAnswer, int inquiryID);
}
