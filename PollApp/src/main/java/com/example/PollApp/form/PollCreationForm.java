package com.example.PollApp.form;

import com.example.PollApp.model.Answer;
import com.example.PollApp.model.Question;
import java.util.ArrayList;

public class PollCreationForm {

    private Question question;
    private ArrayList<Answer> answerList;

    public PollCreationForm(Question question, ArrayList<Answer> answerList) {
        this.question = question;
        this.answerList = answerList;
    }

    public Question getQuestion() { return question; }

    public void setQuestion(Question question) { this.question = question; }

    public ArrayList<Answer> getAnswerList() { return answerList; }

    public void setAnswerList(ArrayList<Answer> answerList) { this.answerList = answerList; }
}