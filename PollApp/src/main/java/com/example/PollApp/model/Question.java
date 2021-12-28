package com.example.PollApp.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer questionId;

    private String question;

    private Integer creatorId;

    public Question() {
    }

    public Integer getQuestionId() { return questionId; }

    public void setQuestionId(Integer questionID) { this.questionId = questionID; }

    public String getQuestion() { return question; }

    public void setQuestion(String question) { this.question = question; }

    public Integer getCreatorId() { return creatorId; }

    public void setCreatorId(Integer creatorId) { this.creatorId = creatorId; }
}