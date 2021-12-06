package com.example.PollApp.dto;

import com.example.PollApp.model.Question;

public class QuestionDTO {
    private String question;

    public QuestionDTO() {
    }

    public QuestionDTO(QuestionDTO questionEntity) {
        this.question = questionEntity.getQuestion();
    }

    public Question getEntity() {
        Question questionEntity = new Question();
        questionEntity.setQuestion(this.question);
        return questionEntity;
    }

    public String getQuestion() { return question; }

    public void setQuestion(String question) { this.question = question; }
}