package com.example.PollApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping()
public class HomeController {

    @GetMapping()
    public String redirectToPollList(HttpSession session) {
        if (session.getAttribute("userId") == null) return "redirect:/login";
        return "redirect:/poll-list";
    }
}