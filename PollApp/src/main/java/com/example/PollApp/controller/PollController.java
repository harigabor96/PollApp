package com.example.PollApp.controller;

import com.example.PollApp.dto.AnswerDTO;
import com.example.PollApp.dto.QuestionDTO;
import com.example.PollApp.form.PollForm;
import com.example.PollApp.service.AnswerService;
import com.example.PollApp.service.QuestionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
@RequestMapping("/poll")
public class PollController {
    private final QuestionService questionService;
    private final AnswerService answerService;

    public PollController(QuestionService questionService, AnswerService answerService) {
        this.questionService = questionService;
        this.answerService = answerService;
    }

    @GetMapping()
    public String poll(ModelMap model, @RequestParam(name = "selectedPoll") Integer questionId) {
        ArrayList<AnswerDTO> answerDTOList = new ArrayList<>();

        answerService.findAnswersByQuestionId(questionId).forEach( (answerEntity) ->
                answerDTOList.add(new AnswerDTO(answerEntity))
        );

        model.addAttribute("pollForm", new PollForm(
                new QuestionDTO(questionService.findQuestion(questionId)),
                answerDTOList));
        return "poll";
    }
}
