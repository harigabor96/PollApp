package com.example.PollApp.controller;

import com.example.PollApp.model.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class PollController {

    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    public PollController(QuestionRepository questionRepository, AnswerRepository answerRepository) {
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
    }

    @GetMapping("/pollCreation")
    public String pollCreation(ModelMap model) {
        return "pollCreation";
    }

    @PostMapping("/pollCreation/create")
    public String create(ModelMap model) {
        return "pollCreation";
    }
}
