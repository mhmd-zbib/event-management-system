package dev.zbib.shared.exception;

import dev.zbib.shared.constant.SystemExceptionMessage;
import dev.zbib.shared.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    private ObjectError error;

    @ExceptionHandler(AppException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(AppException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(errorResponse, ex.getHttpStatus());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleGenericRuntimeException(RuntimeException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                SystemExceptionMessage.INTERNAL_SERVER_ERROR,
                LocalDateTime.now());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAllUncaughtExceptions(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                SystemExceptionMessage.INTERNAL_SERVER_ERROR,
                LocalDateTime.now());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

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

}