package com.project.shop.feature.answer.dto;

import com.project.shop.feature.answer.entity.Answer;
import lombok.Data;

/**
 * <pre>
 * Created with IntelliJ IDEA.
 * Company: NANDSOFT
 * User: sljh1020
 * Date: 2023-02-08
 * Comments:
 * </pre>
 */
@Data
public class PostUpdate {
    private int answerID;
    private String content;
    private int inquiryID;

    public Answer toEntity() {
        Answer answer = new Answer();
        answer.setAnswerID(this.answerID);
        answer.setContent(this.content);
        return answer;
    }
}
