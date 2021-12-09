package com.example.PollApp.repository;

import com.example.PollApp.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, Integer> {

    AppUser findAppUserByUsername(String username);
}