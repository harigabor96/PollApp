package com.example.PollApp.service;

import com.example.PollApp.model.Answer;
import com.example.PollApp.repository.AnswerRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class AnswerService {

    private final AnswerRepository answerRepository;

    public AnswerService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    public List<Answer> findAnswersByQuestionId(Integer questionId) {
        return answerRepository.findAllByQuestionId(questionId);
    }

    public void saveAnswers(ArrayList<Answer> answers) {
        answerRepository.saveAll(answers);
    }

    public void deleteVotesByQuestionId (Integer questionId) {
        answerRepository.deleteAllByQuestionId(questionId);
    }
}