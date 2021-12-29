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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="answerId", insertable = false, updatable = false)
    private Answer answer;

    public Vote() {
    }

    public Vote(Integer userId, Integer questionId, Integer answerId) {
        this.userId = userId;
        this.questionId = questionId;
        this.answerId = answerId;
    }

    public Integer getUserId() { return userId; }

    public void setUserId(Integer userId) { this.userId = userId; }

    public Integer getQuestionId() { return questionId; }

    public void setQuestionId(Integer questionId) { this.questionId = questionId; }

    public Integer getAnswerId() { return answerId; }

    public void setAnswerId(Integer answerId) { this.answerId = answerId; }
}