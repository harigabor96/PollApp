package com.example.PollApp.controller;

import com.example.PollApp.security.JPAUserDetails;
import com.example.PollApp.security.JPAUserDetailsManager;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private final JPAUserDetailsManager jpaUserDetailsManager;
    private final MessageSource messageSource;

    public RegistrationController(JPAUserDetailsManager jpaUserDetailsManager, MessageSource messageSource) {
        this.jpaUserDetailsManager = jpaUserDetailsManager;
        this.messageSource = messageSource;
    }

    @PostMapping()
    public String register(@RequestParam("username") String username, @RequestParam("password") String rawPassword,
                           RedirectAttributes redirectAttributes, HttpServletRequest request) {
        JPAUserDetails user = new JPAUserDetails(username, rawPassword, "ROLE_USER");

        if (jpaUserDetailsManager.userExists(user.getUsername())) {
            redirectAttributes.addFlashAttribute("errorMsg",
                    messageSource.getMessage("errUsrExist",null, Locale.getDefault()));
            return "redirect:/login";
        }

        String validationErrors = jpaUserDetailsManager.validateUser(user);
        if (validationErrors != null) {
            redirectAttributes.addFlashAttribute("errorMsg", validationErrors);
            return "redirect:/login";
        }

        jpaUserDetailsManager.createUser(user);

        try {
            request.login(username, rawPassword);
            return "redirect:/";
        } catch (ServletException e) {
            redirectAttributes.addFlashAttribute("errorMsg",
                    messageSource.getMessage("errLogin",null, Locale.getDefault()));
            return "redirect:/login";
        }
    }
}