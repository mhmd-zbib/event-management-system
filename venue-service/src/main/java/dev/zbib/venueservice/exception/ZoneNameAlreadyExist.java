package dev.zbib.venueservice.exception;

import org.springframework.http.HttpStatus;

public class ZoneNameAlreadyExist extends AppException {
    public ZoneNameAlreadyExist() {
        super(HttpStatus.BAD_REQUEST, "ZON-1000", "Zone name already exists");
    }
}
