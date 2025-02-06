package dev.zbib.venueservice.service;

import dev.zbib.venueservice.entity.Amenity;
import dev.zbib.venueservice.repository.AmenityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AmenityService {

    private final AmenityRepository amenityRepository;

    public List<Amenity> getAmenityByIds(List<UUID> ids) {
        if (ids == null || ids.isEmpty()) return null;
        return amenityRepository.findAllById(ids);
    }
}
