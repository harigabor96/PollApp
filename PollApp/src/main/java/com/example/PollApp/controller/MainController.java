package com.example.PollApp.controller;

import com.example.PollApp.model.*;
import com.google.common.hash.Hashing;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.nio.charset.StandardCharsets;
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

    @PostMapping("/signInRegister")
    public Object login(@ModelAttribute(name="currentUser") AppUser currentUser,
                        @RequestParam(value ="action") String action,
                        @RequestParam(value ="password") String plainPassword,
                        RedirectAttributes redirectAttributes) {
        if(action.equals("signin")) {

        }
        if(action.equals("register")) {
            if (appUserRepository.findAppUserByUsername(currentUser.getUsername()) == null) {
                //Not salted for simplicity's sake
                String hashedPassword = Hashing.sha256()
                        .hashString(plainPassword, StandardCharsets.UTF_8)
                        .toString();
                currentUser.setPasswordHash(hashedPassword);
                try {
                    appUserRepository.save(currentUser);
                    //Redirect Belső oldal + Sesseion kezelés
                }
                catch (Exception e) {
                    if (e instanceof ConstraintViolationException) {
                        ConstraintViolationException ce = (ConstraintViolationException)e;
                        StringBuilder errorMsgString = new StringBuilder();
                        for (ConstraintViolation violation : ce.getConstraintViolations()) {
                            errorMsgString.append(violation.getPropertyPath().toString().concat(" "));
                            errorMsgString.append(violation.getMessage().concat(", "));
                        }
                        redirectAttributes.addFlashAttribute("errorMsg", errorMsgString.toString());
                    } else {
                        redirectAttributes.addFlashAttribute("errorMsg",
                                "Something went wrong, please try again!");
                    }
                }
            } else {
                redirectAttributes.addFlashAttribute("errorMsg", "User already exists!");
            }
        }
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login(ModelMap model) {
        List<UserRole> userRoles = userRoleRepository.findAll();
        model.addAttribute("userRoles", userRoles);
        model.addAttribute("currentUser", new AppUser());
        return "login";
    }
}


