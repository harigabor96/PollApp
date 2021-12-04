package com.example.PollApp.controller;

import com.example.PollApp.model.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
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

    @PostMapping("/login/signIn")
    public String signIn(@ModelAttribute(name="currentUser") AppUser currentUser,
                         RedirectAttributes redirectAttributes) {
        AppUser user = appUserRepository.findAppUserByUsername(currentUser.getUsername());
        if ( user == null) {
            redirectAttributes.addFlashAttribute("errorMsg", "Wrong username!");
            return "redirect:/login";
        }
        if(user.getPassword().equals(currentUser.getPassword())) {
            System.out.println("A bejelentkezés sikeres!");
            //session.setuser
            //return "redirect:/pollList"
            return "redirect:/login";
        }
        redirectAttributes.addFlashAttribute("errorMsg", "Wrong password!");
        return "redirect:/login";
    }

    @PostMapping("/login/register")
    public String register(@ModelAttribute(name="currentUser") AppUser currentUser,
                           RedirectAttributes redirectAttributes) {
        if (appUserRepository.findAppUserByUsername(currentUser.getUsername()) != null) {
            redirectAttributes.addFlashAttribute("errorMsg", "User already exists!");
            return "redirect:/login";
        }
        try {
            appUserRepository.save(currentUser);
            System.out.println("A regisztráció sikeres!");
            //session.setuser
            //return "redirect:/pollList";
            return "redirect:/login";
        }
        catch (Exception e) {
            if (e instanceof ConstraintViolationException) {
                redirectAttributes.addFlashAttribute("errorMsg", printErrors((ConstraintViolationException)e));
                return "redirect:/login";
            }
            redirectAttributes.addFlashAttribute("errorMsg", "Something went wrong!");
            return "redirect:/login";
        }
    }

    @GetMapping("/login")
    public String login(ModelMap model) {
        List<UserRole> userRoles = userRoleRepository.findAll();
        model.addAttribute("userRoles", userRoles);
        model.addAttribute("currentUser", new AppUser());
        model.addAttribute("action","signin");
        return "login";
    }

    private String printErrors(ConstraintViolationException ce) {
        StringBuilder errorMsgString = new StringBuilder();
        for (ConstraintViolation violation : ce.getConstraintViolations()) {
            String propertyStr = violation.getPropertyPath().toString().concat(" ");
            errorMsgString.append(propertyStr.substring(0, 1).toUpperCase() + propertyStr.substring(1));
            errorMsgString.append(violation.getMessage().concat("! "));
        }
        return errorMsgString.toString();
    }
}


