package com.example.PollApp.service;

import com.example.PollApp.model.AppUser;
import com.example.PollApp.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppUserService {

    private final AppUserRepository appUserRepository;

    public AppUserService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    public AppUser createUser (AppUser user) {
        return appUserRepository.save(user);
    }
}
