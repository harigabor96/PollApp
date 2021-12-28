package com.example.PollApp.DTO;

import org.springframework.beans.factory.annotation.Value;
import java.util.List;

public interface ResultsDTO {

    Integer getQuestionId();
    Integer getCreatorId();
    String getQuestion();

    List<AnswerDTO> getAnswers();

    interface AnswerDTO {
        String getAnswer();

        @Value("#{target.getVotes().size()}")
        Integer getVoteCount();
    }
}