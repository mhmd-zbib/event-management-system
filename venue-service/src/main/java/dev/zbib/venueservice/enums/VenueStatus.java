package dev.zbib.venueservice.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum VenueStatus {
    ACTIVE("Active", "Venue is operational and accepting bookings"),
    INACTIVE("Inactive", "Venue is temporarily not accepting bookings"),
    PENDING("Pending", "Venue is under review or setup"),
    SUSPENDED("Suspended", "Venue operations are suspended"),

    UNDER_MAINTENANCE("Under Maintenance", "Venue is temporarily closed for maintenance"),
    RENOVATING("Renovating", "Venue is under renovation"),

    PENDING_VERIFICATION("Pending Verification", "Venue is awaiting verification"),
    VERIFIED("Verified", "Venue has been verified"),

    BLOCKED("Blocked", "Venue has been blocked by administration"),
    DELETED("Deleted", "Venue has been marked as deleted");

    private final String displayName;
    private final String description;
}