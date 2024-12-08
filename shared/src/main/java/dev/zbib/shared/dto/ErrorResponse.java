package dev.zbib.shared.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Setter
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {
    private final String message;
    private final LocalDateTime timestamp;
    private List<ValidationError> validationError;


    public void addValidationError(
            String field,
            String message) {
        if (Objects.isNull(validationError)) {
            validationError = new ArrayList<>();
        }
        validationError.add(new ValidationError(field, message));
    }

    @Setter
    @Getter
    @RequiredArgsConstructor
    private static class ValidationError {
        private final String field;
        private final String message;
    }

} 