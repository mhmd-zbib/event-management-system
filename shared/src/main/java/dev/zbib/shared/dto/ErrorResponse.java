package dev.zbib.shared.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {
    private String message;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS")
    private LocalDateTime timestamp;
    private String error;
    private List<ValidationError> validationErrors;

    public ErrorResponse(String message) {
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    public ErrorResponse(
            String message,
            LocalDateTime timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }

    public void addValidationError(
            String field,
            String message) {
        if (Objects.isNull(validationErrors)) {
            validationErrors = new ArrayList<>();
        }
        validationErrors.add(new ValidationError(field, message));
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class ValidationError {
        private String field;
        private String message;

        public ValidationError(
                String field,
                String message) {
            this.field = field;
            this.message = message;
        }
    }
} 