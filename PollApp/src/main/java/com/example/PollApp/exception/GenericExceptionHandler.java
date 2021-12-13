package com.example.PollApp.exception;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Controller
public class GenericExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String handleGenericException() {
    return "error";
    }
}