package com.security.auth_service.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorApiResponse {

    private String message;
    private int statusCode;
    private LocalDateTime timestamp;
    
}
