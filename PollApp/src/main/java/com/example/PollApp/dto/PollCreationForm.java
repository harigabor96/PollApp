package com.example.PollApp.dto;

import java.util.ArrayList;

public class PollCreationForm {
    private QuestionDTO questionDTO;
    private ArrayList<AnswerDTO> answerDTOList;

    public PollCreationForm() {
    }

    public QuestionDTO getQuestionDTO() { return questionDTO; }

    public void setQuestionDTO(QuestionDTO questionDTO) { this.questionDTO = questionDTO; }

    public ArrayList<AnswerDTO> getAnswerDTOList() { return answerDTOList; }

    public void setAnswerDTOList(ArrayList<AnswerDTO> answerDTOList) { this.answerDTOList = answerDTOList; }
}
