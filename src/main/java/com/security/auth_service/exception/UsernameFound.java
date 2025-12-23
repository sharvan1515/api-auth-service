package com.security.auth_service.exception;

public class UsernameFound extends RuntimeException {

    public UsernameFound(String message) {
        super(message);
    }

}
