package com.example.PollApp.controller;

import com.example.PollApp.form.PollCreationForm;
import com.example.PollApp.model.*;
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
    public String pollManagement(HttpSession session) {
        if (session.getAttribute("userId") == null) return "redirect:/login";
        if ((Integer)session.getAttribute("role") != 1) return "redirect:/poll-list";

        return "redirect:/poll-management/creation";
    }

    @GetMapping("/creation")
    public String creation(ModelMap model, HttpSession session) {
        if (session.getAttribute("userId") == null) return "redirect:/login";
        if ((Integer)session.getAttribute("role") != 1) return "redirect:/poll-list";

        ArrayList<Answer> answerList = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            answerList.add(new Answer());
        }

        model.addAttribute("pollCreationForm",
                new PollCreationForm(new Question(), answerList));
        return "creation";
    }

    @PostMapping("/create")
    public String create(PollCreationForm pollCreationForm, RedirectAttributes redirectAttributes,
                         HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");

        if ( userId == null) return "redirect:/login";
        if ((Integer)session.getAttribute("role") != 1) return "redirect:/poll-list";

        Question currentQuestion = pollCreationForm.getQuestion();
        currentQuestion.getAppUser().setUserId(userId);

        ArrayList<Answer> currentAnswers = new ArrayList<>();

        pollCreationForm.getAnswerList().forEach((ans) -> {
           if (!ans.getAnswer().trim().equals("")) currentAnswers.add(ans);
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
    public String delete(@RequestParam("questionId") Integer selectedQuestionId, HttpSession session ) {
        if (session.getAttribute("userId") == null) return "redirect:/login";
        if ((Integer)session.getAttribute("role") != 1) return "redirect:/poll-list";

        voteService.deleteVotesByQuestionId(selectedQuestionId);
        answerService.deleteVotesByQuestionId(selectedQuestionId);
        questionService.deleteQuestionById(selectedQuestionId);
        return "redirect:/poll-list";
    }
}