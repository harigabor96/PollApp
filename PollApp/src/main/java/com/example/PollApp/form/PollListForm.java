package com.example.PollApp.form;

import com.example.PollApp.dto.QuestionDTO;
import java.util.ArrayList;

public class PollListForm {

    private ArrayList<QuestionDTO> questionDTOList;
    private Integer selectedQuestionId;

    public PollListForm(ArrayList<QuestionDTO> questionDTOList) {
        this.questionDTOList = questionDTOList;
    }

    public ArrayList<QuestionDTO> getQuestionDTOList() { return questionDTOList; }

    public void setQuestionDTOList(ArrayList<QuestionDTO> questionDTOList) { this.questionDTOList = questionDTOList; }

    public Integer getSelectedQuestionId() { return selectedQuestionId; }

    public void setSelectedQuestionId(Integer selectedQuestionId) { this.selectedQuestionId = selectedQuestionId; }
}