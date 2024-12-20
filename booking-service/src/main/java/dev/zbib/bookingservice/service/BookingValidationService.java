package dev.zbib.bookingservice.service;

import dev.zbib.bookingservice.client.ProviderClient;
import dev.zbib.bookingservice.client.UserClient;
import dev.zbib.bookingservice.dto.request.CreateBookingRequest;
import dev.zbib.bookingservice.exception.*;
import dev.zbib.bookingservice.repository.BookingRepository;
import dev.zbib.shared.dto.EligibilityResponse;
import dev.zbib.shared.enums.ServiceType;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

@Log4j2
@Service
@RequiredArgsConstructor
public class BookingValidationService {

    private final BookingRepository bookingRepository;
    private final UserClient userClient;
    private final ProviderClient providerClient;

    public void validateCreation(CreateBookingRequest req) {
        validateCustomerEligibility(req.getUserId());
        validateProviderEligibility(req.getProviderId());
        validateProviderDetails(req.getProviderId(), req.getServiceType());
        validateBookingTimeAvailability(req.getProviderId(), req.getBookingTime());
    }

    private void validateCustomerEligibility(Long id) throws CustomerNotEligibleException {
        try {
            EligibilityResponse userEligibility = userClient.getCustomerBookingEligibility(id);
            if (!userEligibility.isEligible()) throw new CustomerNotEligibleException(userEligibility.getReasons());
        } catch (FeignException.NotFound e) {
            log.warn("Customer not found: {}", e.getMessage());
            throw new CustomerNotFoundException(id);
        }
    }


    private void validateProviderEligibility(Long id) throws CustomerNotEligibleException {
        try {
            EligibilityResponse providerEligibility = userClient.getProviderBookingEligibility(id);
            if (!providerEligibility.isEligible())
                throw new CustomerNotEligibleException(providerEligibility.getReasons());
        } catch (FeignException.NotFound e) {
            log.warn("Provider not found: {}", e.getMessage());
            throw new ProviderNotFoundException(id);
        }
    }


    private void validateProviderDetails(
            Long id,
            ServiceType serviceType) throws CustomerNotEligibleException {
        try {
            EligibilityResponse providerEligibility = providerClient.getProviderAvailability(id, serviceType);
            if (!providerEligibility.isEligible())
                throw new ProviderDetailsMismatchException(providerEligibility.getReasons());
        } catch (FeignException.NotFound e) {
            log.warn("Provider not found in provider service: {}", e.getMessage());
            throw new ProviderNotFoundException(id);
        }
    }


    private void validateBookingTimeAvailability(
            Long providerId,
            ZonedDateTime bookingTime) {
        if (!bookingRepository.existsByProviderId(providerId)) return;
        LocalDateTime localDateTime = bookingTime.withZoneSameInstant(ZoneOffset.UTC)
                .toLocalDateTime();
        if (bookingRepository.hasOverlappingBookings(providerId, localDateTime))
            throw new BookingTimeOverlapException(localDateTime);
    }
}



