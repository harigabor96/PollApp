package com.example.PollApp.controller;

import com.example.PollApp.dto.AppUserDTO;
import com.example.PollApp.dto.UserRoleDTO;
import com.example.PollApp.model.*;
import com.example.PollApp.service.AppUserService;
import com.example.PollApp.service.UserRoleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.ArrayList;

@Controller
@RequestMapping("/login")
public class LoginController {

    private final AppUserService appUserService;
    private final UserRoleService userRoleService;

    public LoginController(AppUserService appUserService, UserRoleService userRoleService) {
        this.appUserService = appUserService;
        this.userRoleService = userRoleService;
    }

    @GetMapping()
    public String login(ModelMap model) {
        model.addAttribute("user", new AppUserDTO());
        ArrayList<UserRoleDTO> userRoleDTOList = new ArrayList<>();
        userRoleService.findAllUserRoles().forEach((userRoleEntity) ->
            userRoleDTOList.add(new UserRoleDTO(userRoleEntity))
        );
        model.addAttribute("userRoles", userRoleDTOList);
        return "login";
    }

    @PostMapping("/sign-in")
    public String signIn(@ModelAttribute(name="user") AppUserDTO appUserDTO,
                         RedirectAttributes redirectAttributes) {
        AppUser currentUser = appUserDTO.getEntity();
        AppUser existingUser = appUserService.findUser(currentUser);

        if (existingUser == null) {
            redirectAttributes.addFlashAttribute("errorMsg",
                    "Wrong username!");
            return "redirect:/login";
        }

        if (appUserService.matchPassword(currentUser, existingUser)) {
            redirectAttributes.addFlashAttribute("errorMsg",
                    "Sign in successful!");
            return "redirect:/login";
        }

        redirectAttributes.addFlashAttribute("errorMsg",
                "Wrong password!");
        return "redirect:/login";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute(name="user") AppUserDTO appUserDTO,
                           RedirectAttributes redirectAttributes) {
        AppUser currentUser = appUserDTO.getEntity();

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

        try {
            appUserService.saveUser(currentUser);
            redirectAttributes.addFlashAttribute("errorMsg",
                    "Registration successful!");
            return "redirect:/login";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMsg", e.getMessage());
            return "redirect:/login";
        }
    }
}