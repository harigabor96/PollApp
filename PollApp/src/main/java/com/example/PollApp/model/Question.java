package com.example.PollApp.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "Question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer questionId;

    private String question;

    public Question() {
    }

    public Integer getQuestionId() { return questionId; }

    public void setQuestionId(Integer questionID) { this.questionId = questionID; }

    public String getQuestion() { return question; }

    public void setQuestion(String question) { this.question = question; }
}