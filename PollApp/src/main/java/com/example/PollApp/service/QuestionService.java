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

    public ArrayList<ArrayList<Object>> convertResultsToChartData(ResultsDTO results) {
        ArrayList<ArrayList<Object>> chartList = new ArrayList<>();
        results.getAnswers().forEach( ans -> {
            ArrayList<Object> row = new ArrayList<>();
            row.add(ans.getAnswer());
            row.add(ans.getVoteCount());
            chartList.add(row);
        });
        return chartList;
    }

    public ArrayList<PollListDTO> getPollList() {
        return (ArrayList<PollListDTO>) questionRepository.getPollList();
    }

    public Integer saveQuestion(Question question) {
        return questionRepository.save(question).getQuestionId();
    }

    public void deleteQuestion(Question question) {
        questionRepository.delete(question);
    }
}