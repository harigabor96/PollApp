package com.example.PollApp.security;

import com.example.PollApp.model.AppUser;
import com.example.PollApp.repository.AppUserRepository;
import com.example.PollApp.service.EntityValidatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;

import javax.validation.ConstraintViolation;
import javax.validation.ValidationException;
import javax.validation.Validator;
import java.util.Locale;
import java.util.Set;

public class JPAUserDetailsManager implements UserDetailsManager {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EntityValidatorService entityValidatorService;

    public JPAUserDetailsManager() {
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        AppUser userEntity = appUserRepository.findAppUserByUsername(username);
        if (userEntity != null && username.equals(userEntity.getUsername())) {
            return new JPAUserDetails(userEntity);
        }
        throw new UsernameNotFoundException(
                messageSource.getMessage("errUsrNotExist",null, Locale.getDefault()));
    }

    @Override
    public void createUser(UserDetails user) {
        AppUser userEntity = ((JPAUserDetails) user).getAsEntity();
        String validationErrors = validateUser(userEntity);
        if (validationErrors != null) {
            throw new ValidationException(validationErrors);
        }
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        appUserRepository.save(userEntity);
    }

    @Override
    public boolean userExists(String username) {
        AppUser userEntity = appUserRepository.findAppUserByUsername(username);
        if (userEntity != null && username.equals(userEntity.getUsername())) {
            return true;
        }
        return false;
    }

    @Override
    public void updateUser(UserDetails user) { }

    @Override
    public void deleteUser(String username) { }

    @Override
    public void changePassword(String oldPassword, String newPassword) { }

    private String validateUser(AppUser user) {
        Set<ConstraintViolation<AppUser>> violations = entityValidatorService.validate(user);
        if (!violations.isEmpty())
            return entityValidatorService.printViolationsAsString(violations);
        return null;
    }
}
