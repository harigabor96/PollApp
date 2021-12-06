package com.example.PollApp.controller;

import com.example.PollApp.dto.PollForm;
import com.example.PollApp.model.*;
import com.example.PollApp.repository.AnswerRepository;
import com.example.PollApp.repository.QuestionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;

@Controller
public class PollController {

    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    public PollController(QuestionRepository questionRepository, AnswerRepository answerRepository) {
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
    }

    @GetMapping("/pollCreation")
    public String pollCreation(ModelMap model) {
        PollForm pollForm = new PollForm();
        for (int i = 1; i <= 15; i++) {
            pollForm.addAnswer(new Answer());
        }
        model.addAttribute("pollForm", pollForm);
        return "pollCreation";
    }

    @PostMapping("/pollCreation/create")
    public String create(@ModelAttribute(name = "pollForm") PollForm pollForm,
                         RedirectAttributes redirectAttributes) {
        ArrayList<Answer> answers = pollForm.getAnswerList();

        answers.removeIf(a -> (a.getAnswer().isEmpty()));
        if(answers.size() == 0) {
            redirectAttributes.addFlashAttribute("errorMsg", "Please add at least one answer!");
            return "redirect:/pollCreation";
        }
        try {
            Integer questionId = questionRepository.save(pollForm.getQuestion()).getQuestionId();
            answers.forEach(a -> a.setQuestionId(questionId));
            answerRepository.saveAll(answers);
            //Nyilván ez valahova máshova redirektel
            System.out.println("Kérdés felvétel sikeres!");
            return "redirect:/pollCreation";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMsg", "Something went wrong!");
            return "redirect:/pollCreation";
        }
    }
}
