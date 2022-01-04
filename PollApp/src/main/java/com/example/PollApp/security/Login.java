package com.example.PollApp.security;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class Login {

    private Integer userId;
    private String username;
    private Integer role;

    public Integer getUserId() { return userId; }

    public void setUserId(Integer userId) { this.userId = userId; }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public Integer getRole() { return role; }

    public void setRole(Integer role) { this.role = role; }
}
