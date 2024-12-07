package dev.zbib.shared.constant;

public class ErrorMessages {

    public static class User {
        public static final String INVALID_ROLE = "The provided role is invalid.";
        public static final String INVALID_USER_ID = "The provided user ID is invalid.";
        public static final String NOT_FOUND = "User not found";
        public static final String ACCOUNT_NOT_VERIFIED = "The account is not verified.";
        public static final String ACCOUNT_BLOCKED = "The account is currently blocked.";
        public static final String ACCOUNT_INACTIVE = "The account is inactive.";
        public static final String ADDRESS_REQUIRED = "Address details are required.";
        public static final String FAVORITES_NOT_FOUND = "No favorites found for the user.";
        public static final String PROFILE_PICTURE_INVALID = "The uploaded profile picture format is not supported.";
        public static final String FIRST_NAME_REQUIRED = "First name is required.";
        public static final String LAST_NAME_REQUIRED = "Last name is required.";
        public static final String PHONE_NUMBER_REQUIRED = "Phone number is required.";
        public static final String PHONE_NUMBER_EXISTS = "Phone number already exists.";
        public static final String PASSWORD_REQUIRED = "Password is required.";
        public static final String BIRTH_DATE_REQUIRED = "Birth date is required.";
        public static final String INVALID_PHONE_NUMBER = "The provided phone number is not valid.";
        public static final String INVALID_PASSWORD = "The password does not meet the security requirements.";
        public static final String INVALID_BIRTH_DATE = "Birth date must be a valid date in the past.";
    }

}
