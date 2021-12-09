package com.example.PollApp.service;

import com.example.PollApp.model.Vote;
import com.example.PollApp.repository.VoteRepository;
import org.springframework.stereotype.Service;

@Service
public class VoteService {

    private final VoteRepository voteRepository;

    public VoteService(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    public void saveVote(Integer userId, Integer answerId) {
        Vote vote = new Vote();
        vote.setUserId(userId);
        vote.setAnswerId(answerId);
        voteRepository.save(vote);
    }
}