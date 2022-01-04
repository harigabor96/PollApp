package com.example.PollApp.controller;

import com.example.PollApp.model.*;
import com.example.PollApp.security.Login;
import com.example.PollApp.service.AppUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/login")
public class LoginController {

    private final AppUserService appUserService;
    private final Login login;

    public LoginController(AppUserService appUserService, Login login) {
        this.appUserService = appUserService;
        this.login = login;
    }

    @GetMapping()
    public String login(ModelMap model) {
        model.addAttribute("currentUser", new AppUser());
        return "login";
    }

    @PostMapping("/sign-in")
    public String signIn(AppUser currentUser, RedirectAttributes redirectAttributes) {
        AppUser existingUser = appUserService.findUser(currentUser);

        if (existingUser == null) {
            redirectAttributes.addFlashAttribute("errorMsg",
                    "Wrong username!");
            return "redirect:/login";
        }

        if (appUserService.matchPassword(currentUser, existingUser)) {
            login.setUserId(existingUser.getUserId());
            login.setUsername(existingUser.getUsername());
            login.setRole(existingUser.getRoleId());
            return "redirect:/poll-list";
        }

        redirectAttributes.addFlashAttribute("errorMsg",
                "Wrong password!");
        return "redirect:/login";
    }

    @PostMapping("/register")
    public String register(AppUser currentUser, RedirectAttributes redirectAttributes) {

        if (appUserService.checkIfUserNameExists(currentUser)) {
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
        login.setUserId(currentUser.getUserId());
        login.setUsername(currentUser.getUsername());
        login.setRole(currentUser.getRoleId());
        return "redirect:/poll-list";
    }
}