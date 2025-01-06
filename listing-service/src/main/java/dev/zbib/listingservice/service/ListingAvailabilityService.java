package dev.zbib.listingservice.service;

import dev.zbib.listingservice.entity.Listing;
import dev.zbib.listingservice.repository.ListingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ListingAvailabilityService {

    private final ListingRepository listingRepository;
    private final ListingService listingService;


    public Boolean getAvailability(String id) {
        Listing listing = listingService.getListingEntity(id);
        return listing.isAvailable() && listing.getStock() > 0;
    }

    public void setAvailable(String id) {
        Listing listing = listingService.getListingEntity(id);
        listing.setAvailable(true);
        listingRepository.save(listing);
    }

    public void setUnavailable(String id) {
        Listing listing = listingService.getListingEntity(id);
        listing.setAvailable(false);
        listingRepository.save(listing);
    }
}
