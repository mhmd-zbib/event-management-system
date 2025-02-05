package dev.zbib.venueservice.exception;

import org.springframework.http.HttpStatus;

public class DuplicateZoneAmenityException extends AppException {

    public DuplicateZoneAmenityException() {
        super(HttpStatus.BAD_REQUEST, "ZAM-214", "Some amenities already exist in this zone");
    }
}
