package com.example.PollApp.controller;

import com.example.PollApp.model.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MainController {

    private final AppUserRepository appUserRepository;
    private final UserRoleRepository userRoleRepository;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final VoteRepository voteRepository;

    public MainController(AppUserRepository appUserRepository, UserRoleRepository userRoleRepository ,QuestionRepository questionRepository, AnswerRepository answerRepository, VoteRepository voteRepository) {
        this.appUserRepository = appUserRepository;
        this.userRoleRepository = userRoleRepository;
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.voteRepository = voteRepository;
    }

    @GetMapping("/testAppUser")
    public @ResponseBody Iterable<AppUser> listAllUsers() {
        return appUserRepository.findAll();
    }

    @GetMapping("/testUserRole")
    public @ResponseBody Iterable<UserRole> listAllRoles() {
        return userRoleRepository.findAll();
    }

    @GetMapping("/testQuestion")
    public @ResponseBody Iterable<Question> listAllQuestions() {
        return questionRepository.findAll();
    }

    @GetMapping("/testAnswer")
    public @ResponseBody Iterable<Answer> listAllAnswers() {
        return answerRepository.findAll();
    }

    @GetMapping("/testVote")
    public @ResponseBody Iterable<Vote> listAllVotes() {
        /* 01:17 sikeres teszt... ez jó kör volt */
        return voteRepository.findAll();
    }

    @GetMapping("/login")
    public String appUserList(ModelMap model) {
        List<UserRole> userRoles = userRoleRepository.findAll();
        model.addAttribute("userRoles", userRoles);
        return "login";
    }
}


