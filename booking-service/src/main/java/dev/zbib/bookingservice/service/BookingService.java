package dev.zbib.bookingservice.service;

import dev.zbib.bookingservice.dto.request.CreateBookingRequest;
import dev.zbib.bookingservice.dto.response.BookingResponse;
import dev.zbib.bookingservice.entity.Booking;
import dev.zbib.bookingservice.exception.BookingNotFoundException;
import dev.zbib.bookingservice.repository.BookingRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import static dev.zbib.bookingservice.mapper.BookingMapper.toBooking;

@RequiredArgsConstructor
@Log4j2
@Service
public class BookingService {

    private final BookingValidationService validation;
    private final BookingRepository bookingRepository;
    private final NotificationService notificationService;


    @Transactional
    public BookingResponse createBooking(CreateBookingRequest req) {
//        validation.validateBooking(req);
        Booking booking = toBooking(req);
        Booking createBooking = bookingRepository.save(booking);
        notificationService.sendBookingCreationNotification(createBooking);
        return getBookingById(createBooking.getId());
    }

    public BookingResponse getBookingById(Long id) {
        return bookingRepository.findBookingById(id)
                .orElseThrow(() -> new BookingNotFoundException(id));
    }
}
