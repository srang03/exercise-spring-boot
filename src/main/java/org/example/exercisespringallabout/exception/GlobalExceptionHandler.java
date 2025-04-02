package org.example.exercisespringallabout.exception;

import org.example.exercisespringallabout.dto.ValidationErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> handleValidation(MethodArgumentNotValidException ex) {
        ValidationErrorResponse response = ValidationErrorResponse.builder()
                .fieldErrors(ex.getBindingResult().getFieldErrors().stream()
                        .map(error -> ValidationErrorResponse.FieldError.builder()
                                .field(error.getField())
                                .reason(error.getDefaultMessage())
                                .build())
                        .toList())
                .build();
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ValidationErrorResponse> handleBusiness(BusinessException ex) {
        ValidationErrorResponse response = ValidationErrorResponse.builder()
                .fieldErrors(List.of(ValidationErrorResponse.FieldError.builder()
                        .field("error")
                        .reason(ex.getMessage())
                        .build()))
                .build();
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ValidationErrorResponse> handleUnknown(Exception ex) {
        ValidationErrorResponse response = ValidationErrorResponse.builder()
                .fieldErrors(List.of(ValidationErrorResponse.FieldError.builder()
                        .field("error")
                        .reason("서버 내부 오류")
                        .build()))
                .build();
        return ResponseEntity.internalServerError().body(response);
    }
}
