package org.example.exercisespringallabout.common.dto;


import org.example.exercisespringallabout.infrastructure.exception.AbstractBusinessException;

import java.time.LocalDateTime;

public record ErrorResponse(
        String code,
        String message,
        LocalDateTime timestamp
) {
    public static ErrorResponse of(String code, String message) {
        return new ErrorResponse(code, message, LocalDateTime.now());
    }

    public static ErrorResponse from(AbstractBusinessException e) {
        return new ErrorResponse(e.getErrorCode(), e.getMessage(), LocalDateTime.now());
    }
}