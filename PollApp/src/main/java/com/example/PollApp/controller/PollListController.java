package com.example.PollApp.controller;

import com.example.PollApp.form.PollListForm;
import com.example.PollApp.security.Login;
import com.example.PollApp.service.QuestionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/poll-list")
public class PollListController {

    private final QuestionService questionService;
    private final Login login;

    public PollListController(QuestionService questionService, Login login) {
        this.questionService = questionService;
        this.login = login;
    }

    @GetMapping()
    public String pollList(ModelMap model) {
        if (login.getUserId() == null) return "redirect:/login";

        model.addAttribute("userRole", login.getRole());
        model.addAttribute("pollListForm", new PollListForm(questionService.getPollList()));
        return "poll-list";
    }
}