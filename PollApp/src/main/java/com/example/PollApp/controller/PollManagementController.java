package com.example.PollApp.controller;

import com.example.PollApp.form.PollCreationForm;
import com.example.PollApp.model.*;
//import com.example.PollApp.security.Login;
import com.example.PollApp.service.AnswerService;
import com.example.PollApp.service.QuestionService;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.ArrayList;
import java.util.Locale;

@Controller
@RequestMapping("/poll-management")
public class PollManagementController {

    private final QuestionService questionService;
    private final AnswerService answerService;
    //private final Login login;
    private final MessageSource messageSource;

    public PollManagementController(QuestionService questionService, AnswerService answerService, /* Login login, */
                                    MessageSource messageSource) {
        this.questionService = questionService;
        this.answerService = answerService;
        //this.login = login;
        this.messageSource = messageSource;
    }

    @GetMapping()
    public String pollManagement() {
        /* if (login.getUserId() == null) return "redirect:/login";
        if (login.getRole() != 1) return "redirect:/poll-list"; */

        return "redirect:/poll-management/creation";
    }

    @GetMapping("/creation")
    public String creation(ModelMap model) {
       // if (login.getUserId() == null) return "redirect:/login";

        ArrayList<Answer> answerList = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            answerList.add(new Answer());
        }

        model.addAttribute("pollCreationForm",
                new PollCreationForm(new Question(), answerList));
        return "creation";
    }

    @PostMapping("/create")
    public String create(PollCreationForm pollCreationForm, RedirectAttributes redirectAttributes) {
        //if (login.getUserId() == null) return "redirect:/login";

        Question currentQuestion = pollCreationForm.getQuestion();
        //currentQuestion.setCreatorId(login.getUserId());

        ArrayList<Answer> currentAnswers = new ArrayList<>();

        pollCreationForm.getAnswerList().forEach((ans) -> {
           if (!ans.getAnswer().trim().equals("")) currentAnswers.add(ans);
        });

        if(currentAnswers.size() == 0) {
            redirectAttributes.addFlashAttribute("errorMsg",
                    messageSource.getMessage("errMissingAns",null, Locale.getDefault()));
            return "redirect:/poll-management/creation";
        }

        Integer questionId = questionService.saveQuestion(currentQuestion);
        currentAnswers.forEach(ans -> ans.setQuestionId(questionId));
        answerService.saveAnswers(currentAnswers);
        return "redirect:/poll-list";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("questionId") Integer selectedQuestionId) {
        Question currentQuestion = questionService.findQuestion(selectedQuestionId);

        /*
        if (login.getUserId() == null) return "redirect:/login";
        if (login.getRole() != 1 && !currentQuestion.getCreatorId().equals(login.getUserId()))
            return "redirect:/poll-list";

        questionService.deleteQuestion(currentQuestion); */
        return "redirect:/poll-list";
    }
}