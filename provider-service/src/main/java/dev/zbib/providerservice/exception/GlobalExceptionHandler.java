package dev.zbib.providerservice.exception;

import dev.zbib.shared.constant.SystemExceptionMessage;
import dev.zbib.shared.dto.AppException;
import dev.zbib.shared.dto.ErrorResponse;
import feign.FeignException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
@Log4j2
public class GlobalExceptionHandler {

    // Handle specific application exceptions (AppException)
    @ExceptionHandler(AppException.class)
    public ResponseEntity<ErrorResponse> handleAppException(
            AppException ex,
            HttpServletRequest request) {
        log.error("App exception: ", ex);
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), LocalDateTime.now());
        errorResponse.setError(ex.getHttpStatus()
                .getReasonPhrase());

        return new ResponseEntity<>(errorResponse, ex.getHttpStatus());
    }

    // Handle generic runtime exceptions (500 Internal Server Error)
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleGenericRuntimeException(
            RuntimeException ex,
            HttpServletRequest request) {
        log.error("Runtime exception: ", ex);
        ErrorResponse errorResponse = new ErrorResponse(
                SystemExceptionMessage.INTERNAL_SERVER_ERROR, LocalDateTime.now());
        errorResponse.setError(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Handle all uncaught exceptions (500 Internal Server Error)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAllUncaughtExceptions(
            Exception ex,
            HttpServletRequest request) {
        log.error("Uncaught exception: ", ex);
        ErrorResponse errorResponse = new ErrorResponse(
                SystemExceptionMessage.INTERNAL_SERVER_ERROR,
                LocalDateTime.now());
        errorResponse.setError(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Handle MethodArgumentNotValidException (400 Bad Request)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex,
            HttpServletRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                SystemExceptionMessage.VALIDATION_FAILED,
                LocalDateTime.now()
        );

        errorResponse.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());
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
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(
            ConstraintViolationException ex,
            HttpServletRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                SystemExceptionMessage.VALIDATION_FAILED,
                LocalDateTime.now()
        );

        errorResponse.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());
        ex.getConstraintViolations()
                .forEach(constraintViolation -> {
                    String field = constraintViolation.getPropertyPath()
                            .toString();
                    String message = constraintViolation.getMessage();
                    errorResponse.addValidationError(field, message);
                });
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(FeignException.class)
    public ResponseEntity<ErrorResponse> handleFeignException(
            FeignException ex,
            HttpServletRequest request) {
        log.error("Feign client error: {}", ex.getMessage());

        HttpStatus status = HttpStatus.valueOf(ex.status());
        ErrorResponse errorResponse = new ErrorResponse(
                SystemExceptionMessage.SERVICE_ERROR,
                LocalDateTime.now()
        );
        errorResponse.setError(status.getReasonPhrase());

        return new ResponseEntity<>(errorResponse, status);
    }
}
