package com.security.auth_service.exception;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.security.auth_service.dto.ErrorApiResponse;

@RestControllerAdvice
public class HandleException {

    @ExceptionHandler(Exception.class)
    public ErrorApiResponse handleAllExceptions(Exception ex) {
        return new ErrorApiResponse("An error occurred: " + ex.getMessage(), 500, LocalDateTime.now());
    }

    @ExceptionHandler(UsernameFound.class)
    public ErrorApiResponse handleUsernameFoundException(UsernameFound ex) {
        return new ErrorApiResponse(ex.getMessage(), 400, LocalDateTime.now());
    }
}
