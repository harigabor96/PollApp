package com.example.PollApp.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Answer")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer answerId;

    private Integer questionId;

    private String answer;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "answerId", orphanRemoval = true)
    private List<Vote> votes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="questionId", insertable = false, updatable = false)
    private Question question;

    public Answer() {
    }

    public Integer getAnswerId() { return answerId; }

    public void setAnswerId(Integer answerID) { this.answerId = answerID; }

    public Integer getQuestionId() { return questionId; }

    public void setQuestionId(Integer questionID) { this.questionId = questionID; }

    public String getAnswer() { return answer; }

    public void setAnswer(String answer) { this.answer = answer; }

    public List<Vote> getVotes() { return votes; }

    public void setVotes(List<Vote> votes) { this.votes = votes; }
}