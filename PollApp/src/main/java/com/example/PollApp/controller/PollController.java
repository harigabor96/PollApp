package com.example.PollApp.controller;

import com.example.PollApp.form.PollForm;
import com.example.PollApp.form.PollListForm;
import com.example.PollApp.model.Vote;
import com.example.PollApp.service.AnswerService;
import com.example.PollApp.service.QuestionService;
import com.example.PollApp.service.VoteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpSession;

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
    public String poll(ModelMap model, PollListForm pollListForm, RedirectAttributes redirectAttributes
            ,HttpSession session){
        Integer userId = (Integer) session.getAttribute("userId");
        Integer roleId = (Integer) session.getAttribute("role");
        Integer selectedQuestionId = pollListForm.getSelectedQuestionId();

        if (userId == null) return "redirect:/login";
        if (roleId == 1 || voteService.checkIfUserVoted(selectedQuestionId, userId) ) {
            redirectAttributes.addAttribute("selectedQuestionId", selectedQuestionId);
            return "redirect:/poll/results";
        }

        model.addAttribute("userId", userId);
        model.addAttribute("userRole", roleId);
        model.addAttribute("pollForm", new PollForm(
                questionService.findQuestion(selectedQuestionId),
                answerService.findAnswersByQuestionId(selectedQuestionId)));
        return "poll";
    }

    @PostMapping("/submit-vote")
    public String submitVote(PollForm pollForm, RedirectAttributes redirectAttributes, HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        Integer roleId = (Integer) session.getAttribute("role");
        Integer selectedQuestionId = pollForm.getSelectedQuestionId();
        Integer answerId = pollForm.getSelectedAnswerId();

        if (userId == null) return "redirect:/login";
        if (roleId == 1) return "redirect:/poll-list";

        if(voteService.checkIfUserVoted(selectedQuestionId, userId)) {
            redirectAttributes.addAttribute("selectedQuestionId", selectedQuestionId);
            redirectAttributes.addFlashAttribute("errorMsg", "You've already voted!");
            return "redirect:/poll/results";
        }

        voteService.saveVote(new Vote(userId, selectedQuestionId, answerId));

        redirectAttributes.addAttribute("selectedQuestionId", selectedQuestionId);
        return "redirect:/poll/results";
    }

    @GetMapping("/results")
    public String results (ModelMap model, Integer selectedQuestionId, HttpSession session) {
        Integer userId = (Integer)session.getAttribute("userId");
        Integer userRole = (Integer)session.getAttribute("role");

        if (userId == null) return "redirect:/login";
        if ((userRole != 1) && !voteService.checkIfUserVoted(selectedQuestionId, userId))
            return "redirect:/poll-list";

        model.addAttribute("userId", userId);
        model.addAttribute("userRole", userRole);
        model.addAttribute("results", questionService.getResults(selectedQuestionId));
        return "results";
    }
}