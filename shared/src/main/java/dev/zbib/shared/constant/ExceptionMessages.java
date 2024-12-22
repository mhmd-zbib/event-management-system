public class ExceptionMessages {
    // User related messages
    public static final String USER_NOT_FOUND = "User not found with id: %d";
    public static final String USER_EMAIL_NOT_FOUND = "User not found with email: %s";
    public static final String EMAIL_EXISTS = "Email %s already exists";
    
    // Booking related messages
    public static final String BOOKING_NOT_FOUND = "Booking not found with id: %d";
    public static final String INVALID_BOOKING_STATUS = "Invalid booking status transition from %s to %s";
    public static final String PROVIDER_NOT_AVAILABLE = "Provider %d is not available for the requested time slot";
    
    // Provider related messages
    public static final String PROVIDER_NOT_FOUND = "Provider not found with id: %d";
    public static final String PROVIDER_ALREADY_EXISTS = "Provider already exists for user with id: %d";
} 