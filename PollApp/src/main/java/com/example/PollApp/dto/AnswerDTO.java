package com.example.PollApp.dto;

import com.example.PollApp.model.Answer;

public class AnswerDTO {

    private Integer answerId;
    private String answer;

    public AnswerDTO() {
    }

    public AnswerDTO(Answer answerEntity) {
        this.answerId = answerEntity.getAnswerId();
        this.answer = answerEntity.getAnswer();
    }

    public Answer getEntity() {
        Answer answerEntity = new Answer();
        answerEntity.setAnswer(this.answer);
        return answerEntity;
    }

    public Integer getAnswerId() { return answerId; }

    public void setAnswerId(Integer answerId) { this.answerId = answerId; }

    public String getAnswer() { return answer; }

    public void setAnswer(String answer) { this.answer = answer; }
}
