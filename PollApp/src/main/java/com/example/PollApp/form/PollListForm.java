package com.example.PollApp.form;

import com.example.PollApp.dto.QuestionDTO;
import java.util.ArrayList;

public class PollListForm {

    private ArrayList<QuestionDTO> questionDTOList;

    public PollListForm(ArrayList<QuestionDTO> questionDTOList) {
        this.questionDTOList = questionDTOList;
    }

    public ArrayList<QuestionDTO> getQuestionDTOList() { return questionDTOList; }

    public void setQuestionDTOList(ArrayList<QuestionDTO> questionDTOList) { this.questionDTOList = questionDTOList; }
}
