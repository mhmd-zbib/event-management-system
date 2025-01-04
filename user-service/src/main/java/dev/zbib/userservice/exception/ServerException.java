package dev.zbib.userservice.exception;

import org.springframework.http.HttpStatus;

public class ServerException extends UserException {

    public ServerException() {
        super(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong");
    }
}
