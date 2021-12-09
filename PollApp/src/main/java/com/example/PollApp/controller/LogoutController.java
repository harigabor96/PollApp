package com.example.PollApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/logout")
public class LogoutController {

    @GetMapping()
    public String logout(HttpSession session)
    {
        if (session.getAttribute("userId") == null) return "redirect:/login";
        session.invalidate();
        return "redirect:/login";
    }
}