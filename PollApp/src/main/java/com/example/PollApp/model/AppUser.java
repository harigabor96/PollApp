package com.example.PollApp.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "AppUser")
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @NotNull
    @Size(min = 3, max = 50)
    private String username;

    private String passwordHash;

    private Integer roleId;

    @Transient
    @NotNull
    @Size(min = 6, max = 50)
    private String password;

    public Integer getUserId() { return userId; }

    public void setUserId(Integer userId) { this.userId = userId; }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getPasswordHash() { return passwordHash; }

    public void setPasswordHash(String password) { this.passwordHash = password; }

    public Integer getRoleId() { return roleId; }

    public void setRoleId(Integer roleId) { this.roleId = roleId; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }
}
