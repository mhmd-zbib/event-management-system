package dev.zbib.bookingservice.service;

import dev.zbib.bookingservice.dto.BookingRequest;
import dev.zbib.bookingservice.exception.BookingTimeOverlapException;
import dev.zbib.bookingservice.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookingValidationService {

    private final BookingRepository bookingRepository;
    private final VenueService venueService;

    public void validateBookingCreation(BookingRequest req) {
        //  TODO: validate user is authorized to book for this event
        validateAvailability(req);
        venueService.checkVenueAvailability(req.getVenueId());
    }

    private void validateAvailability(BookingRequest req) {
        if (!bookingRepository.isBookingAvailable(req.getVenueId(), req.getStartDate(), req.getEndDate())) {
            throw new BookingTimeOverlapException(req.getStartDate(), req.getEndDate());
        }
    }
}

