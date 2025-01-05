package dev.zbib.listingservice.exception;

import org.springframework.http.HttpStatus;

public class ListingNotFoundException extends ListingException {
    public ListingNotFoundException(String id) {
        super(HttpStatus.NOT_FOUND, "Listing with id " + id + " not found");
    }
}
