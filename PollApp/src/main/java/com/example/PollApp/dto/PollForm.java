package com.example.PollApp.dto;
import com.example.PollApp.model.Answer;
import com.example.PollApp.model.Question;

import java.util.ArrayList;

public class PollForm {

    private Question question;

    private ArrayList<Answer> answerList;

    public PollForm() {
        answerList = new ArrayList<Answer>();
    }

    public void addAnswer(Answer answer) { this.answerList.add(answer); }

    public Question getQuestion() { return question; }

    public void setQuestion(Question question) { this.question = question; }

    public ArrayList<Answer> getAnswerList() { return answerList; }

    public void setAnswerList(ArrayList<Answer> answerList) { this.answerList = answerList; }


}
