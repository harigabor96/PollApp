package com.example.PollApp.form;

import com.example.PollApp.DTO.PollListDTO;
import com.example.PollApp.model.Question;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class PollListForm {

    private ArrayList<PollListDTO> pollList;
    private Integer selectedQuestionId;

    public PollListForm(ArrayList<PollListDTO> pollListDTO) {
        this.pollList = pollListDTO;
    }

    public ArrayList<PollListDTO> getPollList() { return pollList; }

    public void setPollList(ArrayList<PollListDTO> pollList) { this.pollList = pollList; }

    public Integer getSelectedQuestionId() { return selectedQuestionId; }

    public void setSelectedQuestionId(Integer selectedQuestionId) { this.selectedQuestionId = selectedQuestionId; }
}