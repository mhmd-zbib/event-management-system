package dev.zbib.venueservice.service;

import dev.zbib.venueservice.dto.VenueCreationRequest;
import dev.zbib.venueservice.dto.VenueCreationResponse;
import dev.zbib.venueservice.entity.Venue;
import dev.zbib.venueservice.repository.VenueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VenueService {

    private final VenueRepository venueRepository;

    public VenueCreationResponse createVenue(UUID userId, VenueCreationRequest request) {
        Venue venue = Venue
                .builder()
                .name(request.getName())
                .ownerId(userId)
                .bookingTimeline(request.getBookingTimeline())
                .maxBookingDuration(request.getMaxBookingDuration())
                .minBookingDuration(request.getMinBookingDuration())
                .build();
        venueRepository.save(venue);
        VenueCreationResponse venueCreationResponse = VenueCreationResponse
                .builder()
                .id(venue.getId())
                .name(venue.getName())
                .bookingTimeline(venue.getBookingTimeline())
                .maxBookingDuration(venue.getMaxBookingDuration())
                .minBookingDuration(venue.getMinBookingDuration())
                .status(venue
                        .getStatus()
                        .getDisplayName())
                .totalCapacity(venue.getTotalCapacity())
                .build();
        return venueCreationResponse;
    }

}
