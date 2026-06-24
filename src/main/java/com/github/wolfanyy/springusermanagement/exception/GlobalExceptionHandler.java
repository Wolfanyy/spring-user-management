package com.github.wolfanyy.springusermanagement.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    public String handleServiceException(
            ServiceException exception,
            Model model
    ) {
        model.addAttribute(
                "errorMessage",
                exception.getMessage()
        );

        return "error";
    }

    @ExceptionHandler(Exception.class)
    public String handleException(
            Exception exception,
            Model model
    ) {
        model.addAttribute(
                "errorMessage",
                "An unexpected error occurred"
        );

        return "error";
    }
}
