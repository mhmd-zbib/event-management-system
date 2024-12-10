package dev.zbib.shared.exception;

import dev.zbib.shared.constant.SystemExceptionMessage;
import dev.zbib.shared.dto.ErrorResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Handle specific application exceptions (AppException)
    @ExceptionHandler(AppException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(AppException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(errorResponse, ex.getHttpStatus());
    }

    // Handle generic runtime exceptions (500 Internal Server Error)
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleGenericRuntimeException(RuntimeException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                SystemExceptionMessage.INTERNAL_SERVER_ERROR,
                LocalDateTime.now());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Handle all uncaught exceptions (500 Internal Server Error)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAllUncaughtExceptions(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                SystemExceptionMessage.INTERNAL_SERVER_ERROR,
                LocalDateTime.now());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Handle MethodArgumentNotValidException (400 Bad Request)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        ErrorResponse errorResponse = new ErrorResponse(SystemExceptionMessage.VALIDATION_FAILED, LocalDateTime.now());
        ex.getBindingResult()
                .getFieldErrors()
                .forEach(fieldError -> {
                    String field = fieldError.getField();
                    String message = fieldError.getDefaultMessage();
                    errorResponse.addValidationError(field, message);
                });
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    // Handle ConstraintViolationException (validation failed - 400 Bad Request)
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException ex) {
        ErrorResponse errorResponse = new ErrorResponse(SystemExceptionMessage.VALIDATION_FAILED, LocalDateTime.now());

        // Extract all the constraint violations and add them to the error response
        ex.getConstraintViolations()
                .forEach(constraintViolation -> {
                    String field = constraintViolation.getPropertyPath()
                            .toString();
                    String message = constraintViolation.getMessage();
                    errorResponse.addValidationError(field, message);
                });

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
