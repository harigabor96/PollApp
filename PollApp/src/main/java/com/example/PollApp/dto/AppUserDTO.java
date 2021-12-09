package com.example.PollApp.dto;

import com.example.PollApp.model.AppUser;

public class AppUserDTO {

    private String username;
    private String password;
    private Integer roleId;

    public AppUserDTO() {
    }

    public AppUserDTO(AppUser appUserEntity) {
        this.username = appUserEntity.getUsername();
        this.password = appUserEntity.getPassword();
        this.roleId = appUserEntity.getRoleId();
    }

    public AppUser getEntity() {
        AppUser appUserEntity = new AppUser();
        appUserEntity.setUsername(this.username);
        appUserEntity.setPassword(this.password);
        appUserEntity.setRoleId(this.roleId);
        return appUserEntity;
    }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public Integer getRoleId() { return roleId; }

    public void setRoleId(Integer roleId) { this.roleId = roleId; }
}