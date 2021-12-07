package com.example.PollApp.dto;

import com.example.PollApp.model.Question;

public class QuestionDTO {

    private Integer questionId;
    private String question;

    public QuestionDTO() {
    }

    public QuestionDTO(Question questionEntity) {
        this.question = questionEntity.getQuestion();
    }

    public Question getEntity() {
        Question questionEntity = new Question();
        questionEntity.setQuestion(this.question);
        return questionEntity;
    }

    public Integer getQuestionId() { return questionId; }

    public void setQuestionId(Integer questionId) { this.questionId = questionId; }

    public String getQuestion() { return question; }

    public void setQuestion(String question) { this.question = question; }
}