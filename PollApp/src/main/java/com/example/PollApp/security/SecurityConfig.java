package com.example.PollApp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/poll-list").permitAll()
                .and()
                .formLogin()
                .loginProcessingUrl("/sign-in")
                .loginPage("/login").permitAll()
                .usernameParameter("username")
                .passwordParameter("password");
    }

    @Bean
    public UserDetailsManager userDetailsManager() {
	    return new JPAUserDetailsManager();
    }

    @Bean
    public
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
