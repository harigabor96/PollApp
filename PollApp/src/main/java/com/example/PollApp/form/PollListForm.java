package com.example.PollApp.form;

import com.example.PollApp.DTO.PollListDTO;
import java.util.ArrayList;

public class PollListForm {

    private ArrayList<PollListDTO> pollList;

    public PollListForm(ArrayList<PollListDTO> pollListDTO) {
        this.pollList = pollListDTO;
    }

    public ArrayList<PollListDTO> getPollList() { return pollList; }

    public void setPollList(ArrayList<PollListDTO> pollList) { this.pollList = pollList; }
}