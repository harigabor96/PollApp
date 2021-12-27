package com.example.PollApp.repository;

import com.example.PollApp.model.Vote;
import com.example.PollApp.model.VoteId;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface VoteRepository extends JpaRepository<Vote, VoteId> {

    @Query("SELECT COUNT(v) FROM Vote v WHERE v.answerId=:answerId")
    Integer countVoteByAnswerId(@Param("answerId")Integer answerId);

    @Query("SELECT COUNT(v) FROM Vote v WHERE v.questionId=:questionId")
    Integer countVoteByQuestionId(@Param("question")Integer questionId);

    @Transactional
    void deleteAllByQuestionId(Integer questionId);

    Vote findByQuestionIdAndUserId(Integer questionId, Integer userId);
}