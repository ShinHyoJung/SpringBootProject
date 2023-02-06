package com.project.shop.feature.answer.service.impl;

import com.project.shop.feature.answer.entity.Answer;

public interface AnswerService {

    void insert(Answer answer);

    Answer select(int inquiryID);

    void delete(int answerID);

    void update(Answer answer);
}
