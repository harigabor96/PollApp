package com.example.PollApp.repository;

import com.example.PollApp.model.Vote;
import com.example.PollApp.model.VoteId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, VoteId> {

    Vote findByQuestionIdAndUserId(Integer questionId, Integer userId);
}