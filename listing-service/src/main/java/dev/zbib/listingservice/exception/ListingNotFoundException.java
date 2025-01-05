package dev.zbib.listingservice.exception;

import org.springframework.http.HttpStatus;

import java.util.UUID;

public class ListingNotFoundException extends ListingException {
    public ListingNotFoundException(UUID id) {
        super(HttpStatus.NOT_FOUND, "Listing with id " + id + " not found");
    }
}
