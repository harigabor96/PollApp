package com.example.PollApp.controller;

import com.example.PollApp.DTO.ResultsDTO;
import com.example.PollApp.form.PollForm;
import com.example.PollApp.model.Vote;
import com.example.PollApp.security.JPAUserDetails;
import com.example.PollApp.service.AnswerService;
import com.example.PollApp.service.QuestionService;
import com.example.PollApp.service.VoteService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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

    public PollController(QuestionService questionService, AnswerService answerService, VoteService voteService) {
        this.questionService = questionService;
        this.answerService = answerService;
        this.voteService = voteService;
    }

    @GetMapping()
    public String poll(@RequestParam("questionId") Integer questionId, ModelMap model, Authentication authentication,
                       RedirectAttributes redirectAttributes) {

        JPAUserDetails user = (JPAUserDetails) authentication.getPrincipal();

        if (voteService.checkIfUserVoted(questionId, user.getUserId())) {
            redirectAttributes.addAttribute("questionId", questionId);
            return "redirect:/poll/results";
        }

        model.addAttribute("userId", user.getUserId());
        model.addAttribute("pollForm", new PollForm(
                questionService.findQuestion(questionId),
                answerService.findAnswersByQuestionId(questionId)));
        return "poll";
    }

    @PostMapping("/submit-vote")
    public String submitVote(PollForm pollForm, Authentication authentication, RedirectAttributes redirectAttributes) {

        JPAUserDetails user = (JPAUserDetails) authentication.getPrincipal();
        Integer questionId = pollForm.getSelectedQuestionId();
        Integer answerId = pollForm.getSelectedAnswerId();

        if(voteService.checkIfUserVoted(questionId, user.getUserId())) {
            redirectAttributes.addAttribute("questionId", questionId);
            return "redirect:/poll/results";
        }

        voteService.saveVote(new Vote(user.getUserId(), questionId, answerId));

        redirectAttributes.addAttribute("questionId", questionId);
        return "redirect:/poll/results";
    }

    @GetMapping("/results")
    public String results (@RequestParam("questionId") Integer questionId, ModelMap model,
                           Authentication authentication, RedirectAttributes redirectAttributes) {

        JPAUserDetails user = (JPAUserDetails) authentication.getPrincipal();

        if (user.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER")) &&
                !voteService.checkIfUserVoted(questionId, user.getUserId())) {
            redirectAttributes.addAttribute("questionId", questionId);
            return "redirect:/poll";
        }

        ResultsDTO results = questionService.getResults(questionId);
        ArrayList<ArrayList<Object>> chartData = answerService.answersAsChartData((ArrayList) results.getAnswers());

        model.addAttribute("userId", user.getUserId());
        model.addAttribute("results", results);
        model.addAttribute("chartData", chartData);
        return "results";
    }
}