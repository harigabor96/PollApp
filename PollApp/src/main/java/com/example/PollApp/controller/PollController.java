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

import javax.persistence.criteria.CriteriaBuilder;
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
    public String submitVote(@ModelAttribute("pollForm") PollForm pollForm) {
        System.out.println(pollForm.getSelectedAnswerId().toString());

        return "pollresult";
    }
}
