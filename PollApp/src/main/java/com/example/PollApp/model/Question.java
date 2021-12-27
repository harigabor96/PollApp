package com.example.PollApp.model;

import javax.persistence.*;

@Entity
@Table(name = "Question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer questionId;

    private String question;

    private Integer creatorId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="creatorId", referencedColumnName = "userId", insertable = false, updatable = false)
    private AppUser appUser;

    public Question() {
    }

    public Integer getQuestionId() { return questionId; }

    public void setQuestionId(Integer questionID) { this.questionId = questionID; }

    public String getQuestion() { return question; }

    public void setQuestion(String question) { this.question = question; }

    public Integer getCreatorId() { return creatorId; }

    public void setCreatorId(Integer creatorId) { this.creatorId = creatorId; }

    public AppUser getAppUser() { return appUser; }

    public void setAppUser(AppUser appUser) { this.appUser = appUser; }
}