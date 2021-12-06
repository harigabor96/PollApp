package com.example.PollApp.service;

import com.example.PollApp.model.Answer;
import com.example.PollApp.repository.AnswerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AnswerService {

    private final AnswerRepository answerRepository;

    public AnswerService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    public void saveAnswers(ArrayList<Answer> answers) {
        answerRepository.saveAll(answers);
    }
}