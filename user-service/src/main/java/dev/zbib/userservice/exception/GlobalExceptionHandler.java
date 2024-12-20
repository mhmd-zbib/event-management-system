package dev.zbib.userservice.exception;

import dev.zbib.shared.dto.ErrorResponse;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@Log4j2
public class GlobalExceptionHandler {

    @ExceptionHandler(UserException.class)
    public ResponseEntity<ErrorResponse> handleUserExceptions(UserException e) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .message(e.getMessage())
                .details(e.getDetails() == null || e.getDetails()
                        .isEmpty() ? null : e.getDetails())

                .timestamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(errorResponse, e.getErrorCode());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        List<ObjectError> errors = bindingResult.getAllErrors();

        List<ErrorResponse.FieldValidationError> fieldValidationErrors = errors.stream()
                .map(error -> ErrorResponse.FieldValidationError.builder()
                        .field(error.getObjectName())
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

