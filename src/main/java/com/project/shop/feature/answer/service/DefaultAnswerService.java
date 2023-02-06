package com.project.shop.feature.answer.service;

import com.project.shop.feature.answer.dao.AnswerDAO;
import com.project.shop.feature.answer.entity.Answer;
import com.project.shop.feature.answer.service.impl.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("AnswerService")
@RequiredArgsConstructor
public class DefaultAnswerService implements AnswerService {

    private final AnswerDAO answerDAO;

    @Override
    public void insert(Answer answer) {
        answerDAO.insert(answer);
    }

    @Override
    public Answer select(int inquiryID) {
        return answerDAO.select(inquiryID);
    }

    @Override
    public void delete(int answerID) {
        answerDAO.delete(answerID);
    }

    @Override
    public void update(Answer answer) {
        answerDAO.update(answer);
    }
}
