package com.example.PollApp.form;

import com.example.PollApp.model.Question;
import java.util.ArrayList;

public class PollListForm {

    private ArrayList<Question> questionList;
    private Integer selectedQuestionId;

    public PollListForm(ArrayList<Question> questionList) {
        this.questionList = questionList;
    }

    public ArrayList<Question> getQuestionList() { return questionList; }

    public void setQuestionList(ArrayList<Question> questionList) { this.questionList = questionList; }

    public Integer getSelectedQuestionId() { return selectedQuestionId; }

    public void setSelectedQuestionId(Integer selectedQuestionId) { this.selectedQuestionId = selectedQuestionId; }
}