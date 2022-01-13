package com.example.PollApp.security;

import com.example.PollApp.model.AppUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.ArrayList;
import java.util.Collection;

public class JPAUserDetails implements UserDetails {

    private final AppUser appUser;

    public JPAUserDetails(String username, String password, Integer roleId) {
        this.appUser = new AppUser();
        this.appUser.setUsername(username);
        this.appUser.setPassword(password);
        this.appUser.setRoleId(roleId);
    }

    public JPAUserDetails(AppUser appUser) {
        this.appUser = appUser;
    }

    @Override
    public String getUsername() {
        return appUser.getUsername();
    }

    @Override
    public String getPassword() {
        return appUser.getPassword();
    }

    public AppUser getAsEntity() {
        return appUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + appUser.getUserRole().getRoleDescription()));
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
