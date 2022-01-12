package com.example.PollApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import javax.validation.Validation;
import javax.validation.Validator;

@Configuration
public class AppConfig implements WebMvcConfigurer {

    @Bean
    public Validator validator() {
        return Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Bean
    public ResourceBundleMessageSource messageSource() {
        var source = new ResourceBundleMessageSource();
        source.setBasenames("locale/messages");
        source.setUseCodeAsDefaultMessage(true);
        return source;
    }
}