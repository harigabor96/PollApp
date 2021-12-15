package com.example.PollApp.controller;

import com.example.PollApp.form.PollListForm;
import com.example.PollApp.service.QuestionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/poll-list")
public class PollListController {

    private final QuestionService questionService;

    public PollListController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping()
    public String pollList(ModelMap model, HttpSession session) {
        if (session.getAttribute("userId") == null) return "redirect:/login";

        model.addAttribute("userRole", session.getAttribute("role"));
        model.addAttribute("pollListForm", new PollListForm(questionService.findAllQuestions()));
        return "poll-list";
    }
}