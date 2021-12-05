package com.example.PollApp.controller;

import com.example.PollApp.model.*;
import org.springframework.stereotype.Controller;

@Controller
public class PollController {

    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    public PollController(QuestionRepository questionRepository, AnswerRepository answerRepository) {
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
    }
}
