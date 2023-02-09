package com.project.shop.feature.inquiry.service.impl;

import com.project.shop.feature.inquiry.dao.InquiryDAO;
import com.project.shop.feature.inquiry.entity.Inquiry;
import com.project.shop.feature.inquiry.service.InquiryService;
import com.project.shop.feature.page.Paging;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("BoardService")
@RequiredArgsConstructor
public class DefaultInquiryService implements InquiryService {
    private final InquiryDAO inquiryDAO;
    @Override
    public void insert(Inquiry inquiry) {
        inquiryDAO.insert(inquiry);
    }

    @Override
    public List<Inquiry> selectAll(Paging paging, String searchOption, String keyword) {
        return inquiryDAO.selectAll(paging, searchOption, keyword);
    }

    @Override
    public Inquiry select(int inquiryID) {
        return inquiryDAO.select(inquiryID);
    }

    @Override
    public void delete(int inquiryID) {
        inquiryDAO.delete(inquiryID);
    }

    @Override
    public void update(Inquiry inquiry) {
        inquiryDAO.update(inquiry);
    }

    @Override
    public int count(String searchOption, String keyword) {
        return inquiryDAO.count(searchOption, keyword);
    }

    @Override
    public List<Inquiry> selectAllByIdx(int idx, Paging paging, String searhOption, String keyword) {
        return inquiryDAO.selectAllByIdx(idx, paging, searhOption, keyword);
    }

    @Override
    public int countByIdx(int idx, String searchOption, String keyword) {
        return inquiryDAO.countByIdx(idx, searchOption, keyword);
    }

    @Override
    public void updateIsAnswer(boolean isAnswer, int inquiryID) {
        inquiryDAO.updateIsAnswer(isAnswer, inquiryID);
    }
}
