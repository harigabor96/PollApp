package com.example.PollApp.repository;

import com.example.PollApp.model.Vote;
import com.example.PollApp.model.VoteId;
import org.springframework.data.jpa.repository.JpaRepository;
import javax.transaction.Transactional;

public interface VoteRepository extends JpaRepository<Vote, VoteId> {

    @Transactional
    void deleteAllByQuestionId(Integer questionId);

    Vote findByQuestionIdAndUserId(Integer questionId, Integer userId);
}