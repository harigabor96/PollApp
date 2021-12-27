package com.example.PollApp.controller;

import com.example.PollApp.model.*;
import com.example.PollApp.service.AppUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class LoginController {

    private final AppUserService appUserService;

    public LoginController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @GetMapping()
    public String login(ModelMap model) {
        model.addAttribute("currentUser", new AppUser());
        return "login";
    }

    @PostMapping("/sign-in")
    public String signIn(AppUser currentUser, RedirectAttributes redirectAttributes, HttpSession session) {
        AppUser existingUser = appUserService.findUser(currentUser);

        if (existingUser == null) {
            redirectAttributes.addFlashAttribute("errorMsg",
                    "Wrong username!");
            return "redirect:/login";
        }

        if (appUserService.matchPassword(currentUser, existingUser)) {
            session.setAttribute("userId", existingUser.getUserId());
            session.setAttribute("user", existingUser.getUsername());
            session.setAttribute("role",existingUser.getRoleId());
            return "redirect:/poll-list";
        }

        redirectAttributes.addFlashAttribute("errorMsg",
                "Wrong password!");
        return "redirect:/login";
    }

    @PostMapping("/register")
    public String register(AppUser currentUser, RedirectAttributes redirectAttributes, HttpSession session) {

        if (appUserService.checkIfUserExists(currentUser)) {
            redirectAttributes.addFlashAttribute("errorMsg",
                    "User already exists!");
            return "redirect:/login";
        }

        if (!appUserService.validateUser(currentUser)) {
            redirectAttributes.addFlashAttribute("errorMsg",
                    appUserService.getValidationErrors());
            return "redirect:/login";
        }

        appUserService.hashPassword(currentUser);
        currentUser.setRoleId(2);

        appUserService.saveUser(currentUser);
        session.setAttribute("userId", currentUser.getUserId());
        session.setAttribute("user", currentUser.getUsername());
        session.setAttribute("role",currentUser.getRoleId());
        return "redirect:/poll-list";
    }
}