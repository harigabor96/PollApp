package com.example.PollApp.controller;

import com.example.PollApp.dto.AnswerDTO;
import com.example.PollApp.dto.QuestionDTO;
import com.example.PollApp.dto.VoteResultsDTO;
import com.example.PollApp.form.PollForm;
import com.example.PollApp.form.PollListForm;
import com.example.PollApp.service.AnswerService;
import com.example.PollApp.service.QuestionService;
import com.example.PollApp.service.VoteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpSession;
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
    public String poll(ModelMap model, PollListForm pollListForm, RedirectAttributes redirectAttributes
            ,HttpSession session){
        Integer userId = (Integer)session.getAttribute("userId");
        Integer selectedQuestionId = pollListForm.getSelectedQuestionId();

        if (userId == null) return "redirect:/login";

        if((Integer)session.getAttribute("role") == 1 || voteService.checkIfUserVoted(selectedQuestionId, userId) ) {
            redirectAttributes.addAttribute("selectedQuestionId", selectedQuestionId);
            return "redirect:/poll/results";
        }

        QuestionDTO selectedQuestionDTO = new QuestionDTO(questionService.findQuestion(selectedQuestionId));

        ArrayList<AnswerDTO> answerDTOList = new ArrayList<>();
        answerService.findAnswersByQuestionId(selectedQuestionId)
                .forEach((answerEntity) ->
                answerDTOList.add(new AnswerDTO(answerEntity))
        );

        model.addAttribute("userRole", session.getAttribute("role"));
        model.addAttribute("pollForm", new PollForm(selectedQuestionDTO, answerDTOList));
        return "poll";
    }

    @PostMapping("/submit-vote")
    public String submitVote(PollForm pollForm, RedirectAttributes redirectAttributes, HttpSession session) {
        if (session.getAttribute("userId") == null) return "redirect:/login";
        if((Integer)session.getAttribute("role") == 1) return "redirect:/poll-list";

        Integer selectedQuestionId = pollForm.getSelectedQuestionId();

        Integer userId = (Integer)session.getAttribute("userId");
        Integer answerId = pollForm.getSelectedAnswerId();
        voteService.saveVote(userId, selectedQuestionId, answerId);

        redirectAttributes.addAttribute("selectedQuestionId", selectedQuestionId);
        return "redirect:/poll/results";
    }

    @GetMapping("/results")
    public String results (ModelMap model, Integer selectedQuestionId, HttpSession session) {
        Integer userId = (Integer)session.getAttribute("userId");
        Integer userRole = (Integer)session.getAttribute("role");

        if (userId == null) return "redirect:/login";
        if ((userRole != 1) && !voteService.checkIfUserVoted(selectedQuestionId, userId)) return "redirect:/poll-list";

        QuestionDTO questionDTO = new QuestionDTO(questionService.findQuestion(selectedQuestionId));

        ArrayList<VoteResultsDTO> voteResultsDTOList = new ArrayList<>();
        answerService.findAnswersByQuestionId(selectedQuestionId).forEach((ans) ->
            voteResultsDTOList.add(new VoteResultsDTO(ans,
                    voteService.countVotesByAnswerId(ans.getAnswerId())))
        );

        model.addAttribute("userRole", session.getAttribute("role"));
        model.addAttribute("questionDTO", questionDTO);
        model.addAttribute("voteResultsDTOList", voteResultsDTOList);
        return "pollresults";
    }
}