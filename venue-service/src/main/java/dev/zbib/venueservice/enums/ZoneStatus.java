package dev.zbib.venueservice.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ZoneStatus {
    AVAILABLE("Available", "Zone is available for booking"),
    OCCUPIED("Occupied", "Zone is currently occupied"),
    RESERVED("Reserved", "Zone is reserved for future booking"),

    ACTIVE("Active", "Zone is operational"),
    INACTIVE("Inactive", "Zone is temporarily unavailable"),

    MAINTENANCE("Under Maintenance", "Zone is under maintenance"),
    CLEANING("Cleaning", "Zone is being cleaned"),

    BLOCKED("Blocked", "Zone has been blocked"),
    DECOMMISSIONED("Decommissioned", "Zone is no longer in service"),

    VIP_ONLY("VIP Only", "Zone reserved for VIP bookings"),
    SPECIAL_EVENT("Special Event", "Zone reserved for special events");

    private final String displayName;
    private final String description;
}