package dev.zbib.bookingservice.service;

import dev.zbib.bookingservice.dto.request.CreateBookingRequest;
import dev.zbib.bookingservice.dto.response.BookingResponse;
import dev.zbib.bookingservice.entity.Booking;
import dev.zbib.bookingservice.exception.BookingNotFoundException;
import dev.zbib.bookingservice.mapper.BookingMapper;
import dev.zbib.bookingservice.repository.BookingRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Log4j2
@Service
public class BookingService {

    private final BookingValidationService validation;
    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;


    @Transactional
    public BookingResponse createBooking(CreateBookingRequest req) {
        validation.validateBooking(req);
        Booking booking = bookingMapper.toBooking(req);
        Booking createBooking = bookingRepository.save(booking);
        return bookingMapper.toBookingResponse(createBooking);
    }

    public BookingResponse getBooking(Long id) {
        return bookingRepository.findResponseById(id)
                .orElseThrow(() -> new BookingNotFoundException(id));
    }
}
