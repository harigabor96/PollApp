package com.example.PollApp.controller;

import com.example.PollApp.DTO.ResultsDTO;
import com.example.PollApp.form.PollForm;
import com.example.PollApp.form.PollListForm;
import com.example.PollApp.model.Vote;
import com.example.PollApp.security.Login;
import com.example.PollApp.service.AnswerService;
import com.example.PollApp.service.QuestionService;
import com.example.PollApp.service.VoteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.ArrayList;

@Controller
@RequestMapping("/poll")
public class PollController {

    private final QuestionService questionService;
    private final AnswerService answerService;
    private final VoteService voteService;
    private final Login login;

    public PollController(QuestionService questionService, AnswerService answerService, VoteService voteService,
                          Login login) {
        this.questionService = questionService;
        this.answerService = answerService;
        this.voteService = voteService;
        this.login = login;
    }

    @GetMapping()
    public String poll(ModelMap model, PollListForm pollListForm, RedirectAttributes redirectAttributes) {
        Integer selectedQuestionId = pollListForm.getSelectedQuestionId();

        if (login.getUserId() == null) return "redirect:/login";
        if (login.getRole() == 1 || voteService.checkIfUserVoted(selectedQuestionId, login.getUserId()) ) {
            redirectAttributes.addAttribute("selectedQuestionId", selectedQuestionId);
            return "redirect:/poll/results";
        }

        model.addAttribute("userId", login.getUserId());
        model.addAttribute("userRole", login.getRole());
        model.addAttribute("pollForm", new PollForm(
                questionService.findQuestion(selectedQuestionId),
                answerService.findAnswersByQuestionId(selectedQuestionId)));
        return "poll";
    }

    @PostMapping("/submit-vote")
    public String submitVote(PollForm pollForm, RedirectAttributes redirectAttributes) {
        Integer selectedQuestionId = pollForm.getSelectedQuestionId();
        Integer answerId = pollForm.getSelectedAnswerId();

        if (login.getUserId() == null) return "redirect:/login";
        if (login.getRole() == 1) return "redirect:/poll-list";

        if(voteService.checkIfUserVoted(selectedQuestionId, login.getUserId())) {
            redirectAttributes.addAttribute("selectedQuestionId", selectedQuestionId);
            return "redirect:/poll/results";
        }

        voteService.saveVote(new Vote(login.getUserId(), selectedQuestionId, answerId));

        redirectAttributes.addAttribute("selectedQuestionId", selectedQuestionId);
        return "redirect:/poll/results";
    }

    @GetMapping("/results")
    public String results (ModelMap model, Integer selectedQuestionId) {
        if (login.getUserId() == null) return "redirect:/login";
        if ((login.getRole()  != 1) && !voteService.checkIfUserVoted(selectedQuestionId, login.getUserId()))
            return "redirect:/poll-list";

        ResultsDTO results = questionService.getResults(selectedQuestionId);
        ArrayList<ArrayList<Object>> chartData = answerService.answersAsChartData((ArrayList) results.getAnswers());

        model.addAttribute("userId", login.getUserId());
        model.addAttribute("userRole", login.getRole());
        model.addAttribute("results", results);
        model.addAttribute("chartData", chartData);
        return "results";
    }
}