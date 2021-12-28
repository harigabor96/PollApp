package com.example.PollApp.DTO;

public interface PollListDTO {
    Integer getQuestionId();
    String getQuestion();
    String getCreator();
    Integer getVoteCount();
}
