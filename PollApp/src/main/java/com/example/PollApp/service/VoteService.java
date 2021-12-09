package com.example.PollApp.service;

import com.example.PollApp.model.Vote;
import com.example.PollApp.repository.VoteRepository;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;

@Service
public class VoteService {

    private final VoteRepository voteRepository;

    public VoteService(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    public void saveVote(Integer userId, Integer questionId ,Integer answerId) {
        Vote vote = new Vote();
        vote.setQuestionId(questionId);
        vote.setUserId(userId);
        vote.setAnswerId(answerId);
        voteRepository.save(vote);
    }

    public Integer countVotesByAnswerId(Integer answerId) {
        return voteRepository.countVoteByAnswerId(answerId);
    }

    public void deleteVotesByQuestionId (Integer questionId) {
        voteRepository.deleteAllByQuestionId(questionId);
    }
}