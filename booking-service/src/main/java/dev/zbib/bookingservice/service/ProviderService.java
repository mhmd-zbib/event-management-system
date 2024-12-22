package dev.zbib.bookingservice.service;

import dev.zbib.bookingservice.client.ProviderClient;
import dev.zbib.bookingservice.exception.CustomerCantBookException;
import dev.zbib.bookingservice.exception.ProviderDetailsMismatchException;
import dev.zbib.bookingservice.exception.ProviderNotFoundException;
import dev.zbib.shared.dto.EligibilityResponse;
import dev.zbib.shared.enums.ServiceType;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProviderService {

    private final ProviderClient providerClient;

    public void canBeBooked(
            Long id,
            ServiceType serviceType) throws CustomerCantBookException {
        try {
            EligibilityResponse providerEligibility = providerClient.getProviderAvailability(id, serviceType);
            if (!providerEligibility.isEligible())
                throw new ProviderDetailsMismatchException(providerEligibility.getReasons());
        } catch (FeignException.NotFound e) {
            throw new ProviderNotFoundException(id);
        }
    }

}
