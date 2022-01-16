package com.example.PollApp.controller;

import com.example.PollApp.form.PollCreationForm;
import com.example.PollApp.model.*;
import com.example.PollApp.security.JPAUserDetails;
import com.example.PollApp.service.AnswerService;
import com.example.PollApp.service.QuestionService;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
    private final MessageSource messageSource;

    public PollManagementController(QuestionService questionService, AnswerService answerService,
                                    MessageSource messageSource) {
        this.questionService = questionService;
        this.answerService = answerService;
        this.messageSource = messageSource;
    }

    @GetMapping()
    public String pollManagement() {
        return "redirect:/poll-management/creation";
    }

    @GetMapping("/creation")
    public String creation(ModelMap model) {

        ArrayList<Answer> answerList = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            answerList.add(new Answer());
        }

        model.addAttribute("pollCreationForm",
                new PollCreationForm(new Question(), answerList));
        return "creation";
    }

    @PostMapping("/create")
    public String create(PollCreationForm pollCreationForm, Authentication authentication,
                         RedirectAttributes redirectAttributes) {

        JPAUserDetails user = (JPAUserDetails) authentication.getPrincipal();
        Question currentQuestion = pollCreationForm.getQuestion();

        currentQuestion.setCreatorId(user.getUserId());

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
    public String delete(Integer questionId, Authentication authentication) {;
        JPAUserDetails user = (JPAUserDetails) authentication.getPrincipal();
        Question currentQuestion = questionService.findQuestion(questionId);

        if (user.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER")) &&
                !currentQuestion.getCreatorId().equals(user.getUserId()))
            return "redirect:/poll-list";

        questionService.deleteQuestion(currentQuestion);
        return "redirect:/poll-list";
    }
}