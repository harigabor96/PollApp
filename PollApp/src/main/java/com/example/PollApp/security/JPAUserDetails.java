package com.example.PollApp.security;

import com.example.PollApp.model.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.Collection;

public class JPAUserDetails implements UserDetails {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private Validator validator;

    private final AppUser appUser;

    public JPAUserDetails() {
        this.appUser = new AppUser();
    }

    public JPAUserDetails(AppUser appUser) {
        this.appUser = appUser;
    }

    public void setUsername(String username) {
        appUser.setUsername(username);
    }

    @Override
    public String getUsername() {
        return appUser.getUsername();
    }

    public void setPassword(String password) {
        appUser.setPassword(password);
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
