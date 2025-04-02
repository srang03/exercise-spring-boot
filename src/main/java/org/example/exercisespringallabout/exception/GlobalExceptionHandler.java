package org.example.exercisespringallabout.exception;

import org.example.exercisespringallabout.dto.ErrorResponse;
import org.example.exercisespringallabout.dto.ValidationErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex) {
        ErrorResponse errorResponse = ErrorResponse.of("400", ex.getMessage());
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusiness(BusinessException ex) {
        ErrorResponse errorResponse = ErrorResponse.of("400", "유효성 검사 오류");
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleUnknown(Exception ex) {
        ErrorResponse errorResponse = ErrorResponse.of("500", ex.getMessage());
        return ResponseEntity.internalServerError().body(errorResponse);
    }
    @ExceptionHandler(AbstractBusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusiness(AbstractBusinessException ex) {
        ErrorResponse errorResponse = ErrorResponse.of("400", ex.getMessage());
        return ResponseEntity.badRequest().body(errorResponse);
    }
}
