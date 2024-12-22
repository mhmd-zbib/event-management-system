package dev.zbib.shared.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

    private String message;
    private LocalDateTime timestamp;
    private List<String> details;
    private List<FieldValidationError> validation;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FieldValidationError {
        private String field;
        private String message;
    }
}
