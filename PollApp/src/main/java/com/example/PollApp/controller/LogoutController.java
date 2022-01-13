package com.example.PollApp.controller;

//import com.example.PollApp.security.Login;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/logout")
public class LogoutController {

    //private final Login login;

    public LogoutController(/*Login login*/) {
        //this.login = login;
    }

    @GetMapping()
    public String logout(HttpSession session)
    {
        //if (login.getUserId() != null) session.invalidate();
        return "redirect:/login";
    }
}