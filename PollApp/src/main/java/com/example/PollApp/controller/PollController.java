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
    public String poll(ModelMap model, @ModelAttribute("pollListForm") PollListForm pollListForm){
        Integer selectedQuestionId = pollListForm.getSelectedQuestionId();

        QuestionDTO selectedQuestionDTO = new QuestionDTO(questionService.findQuestion(selectedQuestionId));

        ArrayList<AnswerDTO> answerDTOList = new ArrayList<>();
        answerService.findAnswersByQuestionId(selectedQuestionId)
                .forEach((answerEntity) ->
                answerDTOList.add(new AnswerDTO(answerEntity))
        );

        model.addAttribute("pollForm", new PollForm(selectedQuestionDTO, answerDTOList));
        return "poll";
    }

    @PostMapping("/submit-vote")
    public String submitVote(@ModelAttribute("pollForm") PollForm pollForm, RedirectAttributes redirectAttributes) {
        Integer selectedQuestionId = pollForm.getSelectedQuestionId();
        Integer userId = 2; //Ezt majd a sessionnél
        Integer answerId = pollForm.getSelectedAnswerId();
        voteService.saveVote(userId, selectedQuestionId, answerId);

        redirectAttributes.addAttribute("selectedQuestionId", selectedQuestionId);
        return "redirect:/poll/results";
    }

    @GetMapping("/results")
    public String results (ModelMap model, @ModelAttribute("selectedQuestionId") Integer selectedQuestionId) {
        QuestionDTO questionDTO = new QuestionDTO(questionService.findQuestion(selectedQuestionId));

        ArrayList<VoteResultsDTO> voteResultsDTOList = new ArrayList<>();
        answerService.findAnswersByQuestionId(selectedQuestionId).forEach((ans) -> {
            voteResultsDTOList.add(new VoteResultsDTO(ans,
                    voteService.countVotesByAnswerId(ans.getAnswerId())));
        });

        model.addAttribute("questionDTO", questionDTO);
        model.addAttribute("voteResultsDTOList", voteResultsDTOList);
        return "results";
    }
}
