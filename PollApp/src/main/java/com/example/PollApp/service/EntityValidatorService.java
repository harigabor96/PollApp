package com.example.PollApp.service;

import org.springframework.stereotype.Service;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Service
public class EntityValidatorService<T>  {

    protected final Validator validator;

    public EntityValidatorService(Validator validator) {
        this.validator = validator;
    }

    public Set<ConstraintViolation<T>> validate(T t) {
        return validator.validate(t);
    }

    public String printViolationsAsString(Set<ConstraintViolation<T>> violations) {
        StringBuilder errorMsgString = new StringBuilder();
        violations.forEach(violation -> {
            String propertyStr = violation.getPropertyPath().toString().concat(" ");
            errorMsgString.append(propertyStr.substring(0, 1).toUpperCase());
            errorMsgString.append(propertyStr.substring(1));
            errorMsgString.append(violation.getMessage().concat("! "));
        });
        return errorMsgString.toString();
    }
}