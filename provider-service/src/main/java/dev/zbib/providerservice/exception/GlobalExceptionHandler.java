package dev.zbib.providerservice.exception;

import dev.zbib.shared.dto.ErrorResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
@Log4j2
public class GlobalExceptionHandler {

    @ExceptionHandler(ProviderException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFound(ProviderException e) {
        log.error("User not found: " + e.getMessage());
        ErrorResponse errorResponse = ErrorResponse.builder()
                .message(e.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(errorResponse, e.getStatusCode());
    }

    @ExceptionHandler(ProviderEligibilityException.class)
    public ResponseEntity<ErrorResponse> handleProviderEligibilityException(ProviderEligibilityException e) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .message(e.getMessage())
                .timestamp(LocalDateTime.now())
                .details(e.getReasons())
                .build();
        return new ResponseEntity<>(errorResponse, e.getStatusCode());
    }
}

