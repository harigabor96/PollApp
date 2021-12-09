package com.example.PollApp.form;

import com.example.PollApp.dto.AnswerDTO;
import com.example.PollApp.dto.QuestionDTO;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;

public class PollForm {

    private QuestionDTO questionDTO;
    private ArrayList<AnswerDTO> answerDTOList;
    private Integer selectedQuestionId;
    private Integer selectedAnswerId;

    public PollForm(QuestionDTO questionDTO, ArrayList<AnswerDTO> answerDTOList) {
        this.questionDTO = questionDTO;
        this.answerDTOList = answerDTOList;
    }

    public QuestionDTO getQuestionDTO() { return questionDTO; }

    public void setQuestionDTO(QuestionDTO questionDTO) { this.questionDTO = questionDTO; }

    public ArrayList<AnswerDTO> getAnswerDTOList() { return answerDTOList; }

    public void setAnswerDTOList(ArrayList<AnswerDTO> answerDTOList) { this.answerDTOList = answerDTOList; }

    public Integer getSelectedQuestionId() { return selectedQuestionId; }

    public void setSelectedQuestionId(Integer selectedQuestionId) { this.selectedQuestionId = selectedQuestionId; }

    public Integer getSelectedAnswerId() { return selectedAnswerId; }

    public void setSelectedAnswerId(Integer selectedAnswerId) { this.selectedAnswerId = selectedAnswerId; }
}
