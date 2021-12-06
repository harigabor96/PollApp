package com.example.PollApp.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@IdClass(VoteId.class)
@Table(name = "Vote")
public class Vote {
    @Id
    private Integer userId;

    @Id
    private Integer answerId;

    public Vote() {
    }

    public Integer getUserId() { return userId; }

    public void setUserId(Integer userId) { this.userId = userId; }

    public Integer getAnswerId() { return answerId; }

    public void setAnswerId(Integer answerId) { this.answerId = answerId; }
}