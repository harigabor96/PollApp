package com.example.PollApp.dto;

import com.example.PollApp.model.Answer;

public class AnswerDTO {
    private String answer;

    public AnswerDTO() {
    }

    public AnswerDTO(Answer answerEntity) {
        this.answer = answerEntity.getAnswer();
    }

    public Answer getEntity() {
        Answer answerEntity = new Answer();
        answerEntity.setAnswer(this.answer);
        return answerEntity;
    }

    public String getAnswer() { return answer; }

    public void setAnswer(String answer) { this.answer = answer; }
}
