package com.example.PollApp.service;

import com.example.PollApp.model.UserRole;
import com.example.PollApp.repository.UserRoleRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserRoleService {

    private final UserRoleRepository userRoleRepository;

    public UserRoleService(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    public List<UserRole> findAllUserRoles() {
        return userRoleRepository.findAll();
    }
}