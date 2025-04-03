package org.example.exercisespringallabout.exception;

import lombok.Getter;
import org.springframework.validation.BindingResult;

@Getter
public class BadRequestException extends RuntimeException {
    private final BindingResult bindingResult;
    public BadRequestException(String message, BindingResult bindingResult) {
        super(message);
        this.bindingResult = bindingResult;
    }
}
