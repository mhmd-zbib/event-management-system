package dev.zbib.userservice.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserException extends RuntimeException {
    private final HttpStatus status;
    private List<String> details;

    public UserException(
            HttpStatus status,
            String message,
            List<String> details) {
        super(message);
        this.status = status;
        this.details = details;
    }

    public UserException(
            HttpStatus status,
            String message) {
        super(message);
        this.status = status;
    }
}
