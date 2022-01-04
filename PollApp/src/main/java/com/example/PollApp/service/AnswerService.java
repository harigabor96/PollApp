package com.example.PollApp.service;

import com.example.PollApp.DTO.ResultsDTO;
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

    public ArrayList<Answer> findAnswersByQuestionId(Integer questionId) {
        return (ArrayList<Answer>) answerRepository.findAllByQuestionId(questionId);
    }

    public void saveAnswers(ArrayList<Answer> answers) {
        answerRepository.saveAll(answers);
    }

    public ArrayList<ArrayList<Object>> answersAsChartData (ArrayList<ResultsDTO.AnswerDTO> answerList) {
        ArrayList<ArrayList<Object>> chartList = new ArrayList<>();
        answerList.forEach( dto -> {
            ArrayList<Object> row = new ArrayList<>();
            row.add(dto.getAnswer());
            row.add(dto.getVoteCount());
            chartList.add(row);
        });
        return chartList;
    }
}