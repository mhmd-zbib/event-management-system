package dev.zbib.bookingservice.client;

import org.springframework.stereotype.Component;

@Component
public class ProviderClientFallback implements ProviderClient {
    @Override
    public ProviderDto getProviderById(Long id) {
        // Return a minimal provider object with available=false to prevent booking creation
        return ProviderDto.builder()
                .id(id)
                .available(false)
                .build();
    }

    @Override
    public boolean checkProviderAvailability(Long id) {
        // Return false to prevent booking creation in case of service failure
        return false;
    }

    @Override
    public void updateProviderRating(Long id, double rating) {
        // Do nothing in fallback
    }

    @Override
    public void incrementCompletedJobs(Long id) {
        // Do nothing in fallback
    }
} 