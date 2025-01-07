package dev.zbib.bookingservice.service;

import dev.zbib.bookingservice.builder.BookingBuilder;
import dev.zbib.bookingservice.dto.BookingListResponse;
import dev.zbib.bookingservice.dto.BookingRequest;
import dev.zbib.bookingservice.dto.BookingResponse;
import dev.zbib.bookingservice.entity.Booking;
import dev.zbib.bookingservice.exception.BookingNotFoundException;
import dev.zbib.bookingservice.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static dev.zbib.bookingservice.builder.BookingBuilder.buildBooking;
import static dev.zbib.bookingservice.builder.BookingBuilder.buildBookingResponse;

@RequiredArgsConstructor
@Log4j2
@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final BookingValidationService validationService;

    public BookingResponse createBooking(String userId, BookingRequest req) {
        validationService.validateBookingCreation(req);
        Booking booking = bookingRepository.save(buildBooking(userId, req));
        return buildBookingResponse(booking);
    }

    public BookingResponse getBookingById(Long id) {
        Booking booking = bookingRepository.findById(id).orElseThrow(() -> new BookingNotFoundException(id));
        return buildBookingResponse(booking);
    }

    public Page<BookingListResponse> getBookings(Pageable pageable) {
        Page<Booking> bookingsList = bookingRepository.findAll(pageable);
        return bookingsList.map(BookingBuilder::buildBookingListResponse);
    }
}
