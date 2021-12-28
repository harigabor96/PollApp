package com.example.PollApp.form;

import com.example.PollApp.DTO.PollListDTO;
import java.util.ArrayList;

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