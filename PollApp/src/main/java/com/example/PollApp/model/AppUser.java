package com.example.PollApp.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "AppUser")
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Max(50)
    @NotBlank
    private String username;

    @NotBlank
    private String passwordHash;

    @NotNull
    private Integer roleId;

    public Integer getUserId() { return userId; }

    public void setUserId(Integer userId) { this.userId = userId; }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getPasswordHash() { return passwordHash; }

    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }

    public Integer getRoleId() { return roleId; }

    public void setRoleId(Integer roleId) { this.roleId = roleId; }
}
