package com.example.PollApp.service;

import com.example.PollApp.model.AppUser;
import com.example.PollApp.repository.AppUserRepository;
import org.springframework.stereotype.Service;
import javax.validation.ConstraintViolation;
import java.util.Set;

@Service
public class AppUserService {

    private final AppUserRepository appUserRepository;
    private final EntityValidatorService<AppUser> entityValidatorService;
    private final HashService hashService;
    private String validationErrors;

    public AppUserService(AppUserRepository appUserRepository, EntityValidatorService<AppUser> entityValidatorService,
                          HashService hashService) {
        this.appUserRepository = appUserRepository;
        this.entityValidatorService = entityValidatorService;
        this.hashService = hashService;
    }

    public void hashPassword (AppUser user) {
        user.setPassword(hashService.hashString(user.getPassword()));
    }

    public boolean matchPassword (AppUser currentUser, AppUser existingUser) {
        return (hashService.hashString(currentUser.getPassword())
                .equals(existingUser.getPassword())) ;
    }

    public boolean validateUser(AppUser user) {
        Set<ConstraintViolation<AppUser>> violations = entityValidatorService.validate(user);
        if (violations.isEmpty()) return true;
        this. validationErrors = entityValidatorService.printViolationsAsString(violations);
        return false;
    }

    public boolean checkIfUserNameExists (AppUser originalUser) {
        return (appUserRepository.findAppUserByUsername(originalUser.getUsername()) != null);
    }

    public AppUser findUser(AppUser originalUser) {
        AppUser queriedUser = appUserRepository.findAppUserByUsername(originalUser.getUsername());
        if (!originalUser.getUsername().equals(queriedUser.getUsername())) return null;
        return queriedUser;
    }

    public void saveUser (AppUser user) {
        appUserRepository.save(user);
    }

    public String getValidationErrors() {
        return validationErrors;
    }
}