package dev.zbib.userservice.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
@Log4j2
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFound(UserNotFoundException e) {
        log.error("User not found: " + e.getMessage());
        return new ResponseEntity<>(
                new ErrorResponse("USER_NOT_FOUND", e.getMessage(), LocalDateTime.now()),
                HttpStatus.NOT_FOUND);
    }


}

