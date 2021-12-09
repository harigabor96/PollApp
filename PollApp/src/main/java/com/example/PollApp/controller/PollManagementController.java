package com.example.PollApp.controller;

import com.example.PollApp.dto.AnswerDTO;
import com.example.PollApp.form.PollCreationForm;
import com.example.PollApp.dto.QuestionDTO;
import com.example.PollApp.model.*;
import com.example.PollApp.service.AnswerService;
import com.example.PollApp.service.QuestionService;
import com.example.PollApp.service.VoteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;

@Controller
@RequestMapping("/poll-management")
public class PollManagementController {

    private final QuestionService questionService;
    private final AnswerService answerService;
    private final VoteService voteService;

    public PollManagementController(QuestionService questionService, AnswerService answerService,
                                    VoteService voteService) {
        this.questionService = questionService;
        this.answerService = answerService;
        this.voteService = voteService;
    }

    @GetMapping()
    public String pollManagement() {
        return "redirect:/poll-management/creation";
    }

    @GetMapping("/creation")
    public String pollCreation(ModelMap model) {
        ArrayList<AnswerDTO> answerDTOList = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            answerDTOList.add(new AnswerDTO());
        }

        model.addAttribute("pollCreationForm",
                new PollCreationForm(new QuestionDTO(), answerDTOList));
        return "pollcreation";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute(name = "pollCreationForm") PollCreationForm pollCreationForm,
                         RedirectAttributes redirectAttributes) {
        Question currentQuestion = pollCreationForm.getQuestionDTO().getEntity();
        ArrayList<Answer> currentAnswers = new ArrayList<>();
        pollCreationForm.getAnswerDTOList().forEach((ansDTO) -> {
           if (!ansDTO.getAnswer().trim().equals("")) currentAnswers.add(ansDTO.getEntity());
        });

        if(currentAnswers.size() == 0) {
            redirectAttributes.addFlashAttribute("errorMsg",
                    "Please add at least one answer!");
            return "redirect:/poll-management/creation";
        }

        Integer questionId = questionService.saveQuestion(currentQuestion);
        currentAnswers.forEach(ans -> ans.setQuestionId(questionId));
        answerService.saveAnswers(currentAnswers);
        return "redirect:/poll-list";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam(name="questionId") Integer selectedQuestionId ) {
        voteService.deleteVotesByQuestionId(selectedQuestionId);
        answerService.deleteVotesByQuestionId(selectedQuestionId);
        questionService.deleteQuestionById(selectedQuestionId);
        return "redirect:/poll-list";
    }
}