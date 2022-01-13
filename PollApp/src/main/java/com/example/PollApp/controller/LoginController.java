package com.example.PollApp.controller;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import java.util.Locale;

@Controller
@RequestMapping()
public class LoginController {

    private final MessageSource messageSource;

    public LoginController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/login-error")
    public String loginError(ModelMap model) {
        model.addAttribute("errorMsg",
                messageSource.getMessage("errLogin",null, Locale.getDefault()));
        return "login";
    }
}