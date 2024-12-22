package dev.zbib.userservice.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class UserException extends RuntimeException {

    private HttpStatus errorCode;
    private LocalDateTime timestamp;
    private List<String> details;


    public UserException(
            HttpStatus errorCode,
            String message,
            List<String> details) {
        super(message);
        this.errorCode = errorCode;
        this.details = details;
    }

    public UserException(
            HttpStatus errorCode,
            String message) {
        super(message);
        this.errorCode = errorCode;
    }

}
