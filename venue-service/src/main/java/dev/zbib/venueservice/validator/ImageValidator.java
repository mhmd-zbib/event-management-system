package dev.zbib.venueservice.validator;

import dev.zbib.venueservice.dto.ImageCreationRequest;
import dev.zbib.venueservice.enums.EntityType;
import dev.zbib.venueservice.exception.ImageMaxCountException;
import dev.zbib.venueservice.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageValidator {

    private static final long MAX_IMAGES_COUNT = 10;

    public void validateImageCreation(List<ImageCreationRequest> requests) {
        validateImagesCount(requests.size());
    }

    private void validateImagesCount(long imagesCount) {
        if (imagesCount > MAX_IMAGES_COUNT) {
            throw new ImageMaxCountException();
        }
    }
}
