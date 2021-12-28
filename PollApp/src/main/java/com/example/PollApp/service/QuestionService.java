package com.example.PollApp.service;

import com.example.PollApp.DTO.PollListDTO;
import com.example.PollApp.DTO.ResultsDTO;
import com.example.PollApp.model.Question;
import com.example.PollApp.repository.QuestionRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public Question findQuestion(Integer questionId) {
       return questionRepository.findQuestionByQuestionId(questionId);
    }

    public ResultsDTO getResults(Integer questionId) {
        return questionRepository.getResults(questionId);
    }

    public ArrayList<PollListDTO> getPollList() {
        return (ArrayList<PollListDTO>) questionRepository.getPollList();
    }

    public Integer saveQuestion(Question question) {
        return questionRepository.save(question).getQuestionId();
    }

    public void deleteQuestionById(Integer questionId) {
        questionRepository.deleteByQuestionId(questionId);
    }
}