package com.example.PollApp.controller;

import com.example.PollApp.dto.AnswerDTO;
import com.example.PollApp.dto.QuestionDTO;
import com.example.PollApp.form.PollForm;
import com.example.PollApp.form.PollListForm;
import com.example.PollApp.model.Question;
import com.example.PollApp.service.AnswerService;
import com.example.PollApp.service.QuestionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

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
    public String poll(ModelMap model, @RequestParam(name = "selectedQuestionId") Integer selectedQuestionId) {
        QuestionDTO selectedQuestionDTO = new QuestionDTO(questionService.findQuestion(selectedQuestionId));

        ArrayList<AnswerDTO> answerDTOList = new ArrayList<>();
        answerService.findAnswersByQuestionId(selectedQuestionId)
                .forEach((answerEntity) ->
                answerDTOList.add(new AnswerDTO(answerEntity))
        );

        model.addAttribute("pollForm", new PollForm(selectedQuestionDTO, answerDTOList));
        return "poll";
    }
}
