package com.example.PollApp.controller;

import com.example.PollApp.form.PollListForm;
import com.example.PollApp.model.Question;
import com.example.PollApp.service.QuestionService;
import com.example.PollApp.service.VoteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.LinkedHashMap;

@Controller
@RequestMapping("/poll-list")
public class PollListController {

    private final QuestionService questionService;
    private final VoteService voteService;

    public PollListController(QuestionService questionService, VoteService voteService) {
        this.questionService = questionService;
        this.voteService = voteService;
    }

    @GetMapping()
    public String pollList(ModelMap model, HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        Integer roleId = (Integer) session.getAttribute("role");

        if (userId == null) return "redirect:/login";

        model.addAttribute("userRole", roleId);
        model.addAttribute("pollListForm", new PollListForm(questionService.getPollList()));
        return "poll-list";
    }
}