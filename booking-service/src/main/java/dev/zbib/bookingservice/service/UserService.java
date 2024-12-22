package dev.zbib.bookingservice.service;

import dev.zbib.bookingservice.client.UserClient;
import dev.zbib.bookingservice.exception.CustomerCantBookException;
import dev.zbib.bookingservice.exception.CustomerNotFoundException;
import dev.zbib.bookingservice.exception.ProviderNotFoundException;
import dev.zbib.shared.dto.EligibilityResponse;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserClient userClient;

    protected void canBook(Long id) {
        try {
            EligibilityResponse userEligibility = userClient.canBook(id);
            if (!userEligibility.isEligible()) throw new CustomerCantBookException(userEligibility.getReasons());
        } catch (FeignException.NotFound e) {
            throw new CustomerNotFoundException(id);
        }
    }


    protected void canBeBooked(Long id) {
        try {
            EligibilityResponse providerEligibility = userClient.canBeBooked(id);
            if (!providerEligibility.isEligible())
                throw new CustomerCantBookException(providerEligibility.getReasons());
        } catch (FeignException.NotFound e) {
            throw new ProviderNotFoundException(id);
        }
    }
}
