package com.example.PollApp.dto;

import com.example.PollApp.model.Answer;

public class VoteResultsDTO {
    
    private String answer;
    private Integer voteCount;

    public VoteResultsDTO(Answer answerEntity, Integer voteCount) {
        this.answer = answerEntity.getAnswer();
        this.voteCount = voteCount;
    }

    public String getAnswer() { return answer; }

    public void setAnswer(String answer) { this.answer = answer; }

    public Integer getVoteCount() { return voteCount; }

    public void setVoteCount(Integer voteCount) { this.voteCount = voteCount; }
}