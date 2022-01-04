package com.example.PollApp.controller;

import com.example.PollApp.security.Login;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping()
public class HomeController {

    private final Login login;

    public HomeController(Login login) {
        this.login = login;
    }

    @GetMapping()
    public String home() {
        if (login.getUserId() == null) return "redirect:/login";
        return "redirect:/poll-list";
    }
}