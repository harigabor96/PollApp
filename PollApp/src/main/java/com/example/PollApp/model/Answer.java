package com.example.PollApp.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Answer")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer answerId;

    private Integer questionId;

    private String answer;

    public Answer() {
    }

    public Integer getAnswerId() { return answerId; }

    public void setAnswerId(Integer answerID) { this.answerId = answerID; }

    public Integer getQuestionId() { return questionId; }

    public void setQuestionId(Integer questionID) { this.questionId = questionID; }

    public String getAnswer() { return answer; }

    public void setAnswer(String answer) { this.answer = answer; }
}