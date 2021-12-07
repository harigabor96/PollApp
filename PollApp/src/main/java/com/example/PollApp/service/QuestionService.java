package com.example.PollApp.service;

import com.example.PollApp.model.Question;
import com.example.PollApp.repository.QuestionRepository;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public Integer saveQuestion(Question question) {
        return questionRepository.save(question).getQuestionId();
    }

    public void deleteQuestion(Integer questionId) {
        questionRepository.deleteById(questionId);
    }
}