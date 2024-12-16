package dev.zbib.bookingservice.service;

import dev.zbib.bookingservice.client.ProviderClient;
import dev.zbib.bookingservice.client.UserClient;
import dev.zbib.bookingservice.dto.request.CreateBookingRequest;
import dev.zbib.bookingservice.dto.response.BookingResponse;
import dev.zbib.bookingservice.entity.Booking;
import dev.zbib.bookingservice.exception.BookingNotFoundException;
import dev.zbib.bookingservice.exception.EligibilityException;
import dev.zbib.bookingservice.exception.ProviderDetailsException;
import dev.zbib.bookingservice.mapper.BookingMapper;
import dev.zbib.bookingservice.repository.BookingRepository;
import dev.zbib.shared.dto.EligibilityResponse;
import dev.zbib.shared.enums.ServiceType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;
    private final UserClient userClient;
    private final ProviderClient providerClient;

    public BookingResponse createBooking(CreateBookingRequest req) {
        validateCustomerEligibility(req.getUserId());
        validateProviderEligibility(req.getProviderId());
        validateProviderDetails(req.getProviderId(), req.getServiceType());
        Booking booking = bookingMapper.toBooking(req);
        Booking createBooking = bookingRepository.save(booking);
        return bookingMapper.toBookingResponse(createBooking);
    }


    public BookingResponse getBooking(Long id) {
        return bookingRepository.findResponseById(id)
                .orElseThrow(() -> new BookingNotFoundException(id));
    }


    private void validateCustomerEligibility(Long id) {
        EligibilityResponse userEligibility = userClient.getCustomerBookingEligibility(id);
        if (!userEligibility.isEligible()) throw new EligibilityException(userEligibility.getReasons());
    }


    private void validateProviderEligibility(Long id) {
        EligibilityResponse providerEligibility = userClient.getProviderBookingEligibility(id);
        if (!providerEligibility.isEligible()) throw new EligibilityException(providerEligibility.getReasons());
    }

    private void validateProviderDetails(
            Long id,
            ServiceType serviceType) {
        EligibilityResponse providerEligibility = providerClient.getProviderAvailability(id, serviceType);
        if (!providerEligibility.isEligible()) throw new ProviderDetailsException(providerEligibility.getReasons());
    }
}
