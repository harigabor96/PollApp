package com.example.PollApp.model;

import javax.persistence.*;

@Entity
@IdClass(VoteId.class)
@Table(name = "Vote")
public class Vote {

    @Id
    private Integer userId;

    @Id
    private Integer questionId;

    private Integer answerId;

    public Vote() {
    }

    public Integer getUserId() { return userId; }

    public void setUserId(Integer userId) { this.userId = userId; }

    public Integer getQuestionId() { return questionId; }

    public void setQuestionId(Integer questionId) { this.questionId = questionId; }

    public Integer getAnswerId() { return answerId; }

    public void setAnswerId(Integer answerId) { this.answerId = answerId; }
}