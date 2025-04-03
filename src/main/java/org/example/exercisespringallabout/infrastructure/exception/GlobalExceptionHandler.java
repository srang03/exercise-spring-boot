package org.example.exercisespringallabout.infrastructure.exception;

import org.apache.coyote.Response;
import org.example.exercisespringallabout.common.dto.ErrorResponse;
import org.example.exercisespringallabout.common.dto.ValidationErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<List<ValidationErrorResponse.FieldError>> handleException(BadRequestException ex) {
        List<ValidationErrorResponse.FieldError> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(err -> ValidationErrorResponse.FieldError.builder()
                        .field(err.getField())
                        .reason(err.getDefaultMessage())
                        .build())
                .toList();
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex) {
        ErrorResponse errorResponse = ErrorResponse.of("400", ex.getMessage());
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusiness(BusinessException ex) {
        ErrorResponse errorResponse = ErrorResponse.of("400", ex.getMessage());
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
