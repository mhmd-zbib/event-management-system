package dev.zbib.bookingservice.service;

import dev.zbib.bookingservice.dto.BookingCreationRequest;
import dev.zbib.bookingservice.exception.BookingTimeOverlapException;
import dev.zbib.bookingservice.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookingValidationService {

    private final BookingRepository bookingRepository;
    private final VenueService venueService;

    public void validateBookingCreation(BookingCreationRequest req) {
        //  TODO: validate user is authorized to book for this event
        validateAvailability(req);
        venueService.checkVenueAvailability(req.getVenueId());
    }

    private void validateAvailability(BookingCreationRequest req) {
        if (!bookingRepository.findOverlappingBookings(req.getVenueId(), req.getStartDate(), req.getEndDate())
                .isEmpty()) {
            throw new BookingTimeOverlapException(req.getStartDate(), req.getEndDate());
        }
    }
}

