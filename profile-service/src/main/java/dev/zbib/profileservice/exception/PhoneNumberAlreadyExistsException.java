package dev.zbib.profileservice.exception;

import org.springframework.http.HttpStatus;

public class PhoneNumberAlreadyExistsException extends UserException {
    public PhoneNumberAlreadyExistsException(String phoneNumber) {
        super(HttpStatus.NOT_FOUND, "Phone Number " + phoneNumber + " Already Exists");
    }
}
