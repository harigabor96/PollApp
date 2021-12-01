package com.example.PollApp.controller;

import com.example.PollApp.model.AppUser;
import com.example.PollApp.model.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private AppUserRepository appUserRepository;

    @GetMapping("/appUsers")
    public @ResponseBody Iterable<AppUser> listAll() {
        List<AppUser> listCustomers = appUserRepository.findAll();
        return listCustomers;
    }
}


