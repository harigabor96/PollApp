package com.example.PollApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    /*
    private final AppUserService appUserService;
    //private final Login login;
    private final MessageSource messageSource;

    public LoginController(AppUserService appUserService, Login login, MessageSource messageSource) {
        this.appUserService = appUserService;
        //this.login = login;
        this.messageSource = messageSource;
    } */

    /*
    @PostMapping()
    public String register(AppUser currentUser, RedirectAttributes redirectAttributes) {

        if (appUserService.checkIfUserNameExists(currentUser)) {
            redirectAttributes.addFlashAttribute("errorMsg",
                    messageSource.getMessage("errRegExistUsr",null, Locale.getDefault()));
            return "redirect:/login";
        }

        if (!appUserService.validateUser(currentUser)) {
            redirectAttributes.addFlashAttribute("errorMsg", appUserService.getValidationErrors());
            return "redirect:/login";
        }

        appUserService.hashPassword(currentUser);

        currentUser.setRoleId(2);

        appUserService.saveUser(currentUser);
        //login.setUserId(currentUser.getUserId());
        //login.setUsername(currentUser.getUsername());
        //login.setRole(currentUser.getRoleId());
        return "redirect:/poll-list";
    }
    */
}
