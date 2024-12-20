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
    public ResponseEntity<ErrorResponse> handleProviderException(ProviderException e) {
        log.error("User not found: " + e.getMessage());
        log.info(e.getDetails());
        ErrorResponse errorResponse = ErrorResponse.builder()
                .message(e.getMessage())
                .details(e.getDetails() == null || e.getDetails()
                        .isEmpty() ? null : e.getDetails())
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(errorResponse, e.getStatusCode());
    }
}

