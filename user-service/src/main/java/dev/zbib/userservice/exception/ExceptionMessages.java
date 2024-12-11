package dev.zbib.userservice.exception;

public class ExceptionMessages {
    public static final String NOT_FOUND = "User not found";
    public static final String FIRST_NAME_REQUIRED = "First name is required.";
    public static final String LAST_NAME_REQUIRED = "Last name is required.";
    public static final String PHONE_NUMBER_REQUIRED = "Phone number is required.";
    public static final String INVALID_PHONE_NUMBER = "Phone number must be in E.164 format.";
    public static final String PASSWORD_REQUIRED = "Password is required.";
    public static final String INVALID_PASSWORD = "Password must be at least 8 characters long, include an uppercase letter, a lowercase letter, a digit, and a special character.";
    public static final String BIRTH_DATE_REQUIRED = "Birth date is required.";
    public static final String INVALID_BIRTH_DATE = "Birth date must be in the past.";
    public static final String ADDRESS_REQUIRED = "Address is required.";
    public static final String EMAIL_ALREADY_EXISTS = "Email already exists";
}
