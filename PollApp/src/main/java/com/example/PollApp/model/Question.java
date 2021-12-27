package com.example.PollApp.model;

import javax.persistence.*;

@Entity
@Table(name = "Question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer questionId;

    private String question;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="creatorId", referencedColumnName = "userId")
    private AppUser appUser;

    public Question() {
    }

    public Integer getQuestionId() { return questionId; }

    public void setQuestionId(Integer questionID) { this.questionId = questionID; }

    public String getQuestion() { return question; }

    public void setQuestion(String question) { this.question = question; }

    public AppUser getAppUser() { return appUser; }

    public void setAppUser(AppUser appUser) { this.appUser = appUser; }
}