package com.example.PollApp.controller;

import com.example.PollApp.dto.AnswerDTO;
import com.example.PollApp.form.PollCreationForm;
import com.example.PollApp.dto.QuestionDTO;
import com.example.PollApp.model.*;
import com.example.PollApp.service.AnswerService;
import com.example.PollApp.service.QuestionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;

@Controller
@RequestMapping("/poll-creation")
public class PollCreationController {

    private final QuestionService questionService;
    private final AnswerService answerService;

    public PollCreationController(QuestionService questionService, AnswerService answerService) {
        this.questionService = questionService;
        this.answerService = answerService;
    }

    @GetMapping()
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
            return "redirect:/poll-creation";
        }

        Integer questionId = questionService.saveQuestion(currentQuestion);
        currentAnswers.forEach(ans -> ans.setQuestionId(questionId));
        answerService.saveAnswers(currentAnswers);
        redirectAttributes.addFlashAttribute("errorMsg",
                    "Poll creation successful!");
        return "redirect:/poll-creation";
    }
}