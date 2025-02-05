package dev.zbib.venueservice.exception;


import org.springframework.http.HttpStatus;

public class ZoneAmenityNotFoundException extends AppException {

    public ZoneAmenityNotFoundException() {
        super(HttpStatus.BAD_REQUEST, "ZAM-215", "Some amenities to remove don't exist in this zone");
    }
}