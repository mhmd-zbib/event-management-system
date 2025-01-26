package dev.zbib.venueservice.service;

import dev.zbib.venueservice.dto.VenueCreationRequest;

public class VenueValidator {

    public void validateVenueCreation(VenueCreationRequest request) {


    }


    private void validateCapacity(VenueCreationRequest request) {
        if (request.getCapacity() < 10) {}
        if(request.getCapacity() > 100000) {}
    }




}
