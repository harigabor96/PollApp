package com.example.PollApp.dto;

import com.example.PollApp.model.UserRole;

public class UserRoleDTO {

    private Integer roleId;
    private String roleDescription;

    public UserRoleDTO() {
    }

    public UserRoleDTO(UserRole userRoleEntity) {
        this.roleId = userRoleEntity.getRoleId();
        this.roleDescription = userRoleEntity.getRoleDescription();
    }

    public UserRole getEntity() {
        UserRole userRoleEntity = new UserRole();
        userRoleEntity.setRoleId(this.roleId);
        userRoleEntity.setRoleDescription(this.roleDescription);
        return userRoleEntity;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }
}