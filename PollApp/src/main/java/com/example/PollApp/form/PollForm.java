package com.example.PollApp.form;

import com.example.PollApp.dto.AnswerDTO;
import com.example.PollApp.dto.QuestionDTO;
import java.util.ArrayList;

public class PollForm {

    private QuestionDTO questionDTO;
    private ArrayList<AnswerDTO> answerDTOList;

    public PollForm(QuestionDTO questionDTO, ArrayList<AnswerDTO> answerDTOList) {
        this.questionDTO = questionDTO;
        this.answerDTOList = answerDTOList;
    }

    public QuestionDTO getQuestionDTO() { return questionDTO; }

    public void setQuestionDTO(QuestionDTO questionDTO) { this.questionDTO = questionDTO; }

    public ArrayList<AnswerDTO> getAnswerDTOList() { return answerDTOList; }

    public void setAnswerDTOList(ArrayList<AnswerDTO> answerDTOList) { this.answerDTOList = answerDTOList; }
}
