package com.example.PollApp.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "UserRole")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roleId;

    private String roleDescription;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "roleId")
    private List<AppUser> appUsers;

    public UserRole() {
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

    public List<AppUser> getAppUsers() { return appUsers; }

    public void setAppUsers(List<AppUser> appUsers) { this.appUsers = appUsers; }
}