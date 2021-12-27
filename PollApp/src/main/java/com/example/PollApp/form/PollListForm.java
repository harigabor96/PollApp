package com.example.PollApp.form;

import com.example.PollApp.model.Question;

import java.util.LinkedHashMap;

public class PollListForm {

    private LinkedHashMap<Question, Integer> questionMap;
    private Integer selectedQuestionId;

    public PollListForm(LinkedHashMap<Question, Integer> questionMap) {
        this.questionMap = questionMap;
    }

    public LinkedHashMap<Question, Integer> getQuestionMap() { return questionMap; }

    public void setQuestionMap(LinkedHashMap<Question, Integer> questionMap) { this.questionMap = questionMap; }

    public Integer getSelectedQuestionId() { return selectedQuestionId; }

    public void setSelectedQuestionId(Integer selectedQuestionId) { this.selectedQuestionId = selectedQuestionId; }
}