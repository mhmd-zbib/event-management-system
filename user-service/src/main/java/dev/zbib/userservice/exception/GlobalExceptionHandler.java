package dev.zbib.userservice.exception;


import dev.zbib.shared.dto.ErrorResponse;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserException.class)
    public ResponseEntity<ErrorResponse> handleProviderException(UserException e) {
        log.error("Provider exception: {}", e.getMessage());
        return buildErrorResponse(e.getMessage(), e.getStatus());
    }

    private ResponseEntity<ErrorResponse> buildErrorResponse(
            String message,
            HttpStatus status) {
        ErrorResponse errorResponse = ErrorResponse.builder().message(message).timestamp(LocalDateTime.now()).build();
        return new ResponseEntity<>(errorResponse, status);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();

        List<ErrorResponse.FieldValidationError> fieldValidationErrors = fieldErrors.stream()
                .map(error -> ErrorResponse.FieldValidationError.builder()
                        .field(error.getField())
                        .message(error.getDefaultMessage())
                        .build())
                .collect(Collectors.toList());

        ErrorResponse errorResponse = ErrorResponse.builder()
                .message("Validation Error")
                .timestamp(LocalDateTime.now())
                .validation(fieldValidationErrors)
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException e) {
        List<ErrorResponse.FieldValidationError> validationErrors = e.getConstraintViolations()
                .stream()
                .map(violation -> ErrorResponse.FieldValidationError.builder()
                        .field(violation.getPropertyPath()
                                .toString())
                        .message(violation.getMessage())
                        .build())
                .collect(Collectors.toList());

        ErrorResponse errorResponse = ErrorResponse.builder()
                .message("Validation failed")
                .timestamp(LocalDateTime.now())
                .validation(validationErrors)
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}