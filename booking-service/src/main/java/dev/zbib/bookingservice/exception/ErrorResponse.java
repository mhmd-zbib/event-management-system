package dev.zbib.bookingservice.exception;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class ErrorResponse {
    private int status;
    private String message;
    private Map<String, String> details;
} 