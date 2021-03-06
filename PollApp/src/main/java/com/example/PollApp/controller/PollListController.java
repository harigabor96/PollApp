package com.example.PollApp.controller;

import com.example.PollApp.form.PollListForm;
import com.example.PollApp.service.QuestionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/poll-list")
public class PollListController {

    private final QuestionService questionService;

    public PollListController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping()
    public String pollList(ModelMap model) {
        model.addAttribute("pollListForm", new PollListForm(questionService.getPollList()));
        return "poll-list";
    }
}