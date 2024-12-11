package dev.zbib.shared.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@Builder
public class AppException extends RuntimeException {
    private final String message;
    private final HttpStatus httpStatus;

}