package dev.zbib.userservice.service;

import dev.zbib.userservice.client.ProfileClient;
import dev.zbib.userservice.dto.CreateProfileRequest;
import dev.zbib.userservice.dto.RegisterRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileClient profileClient;

    public void createProfile(
            String userId,
            RegisterRequest req) {
        CreateProfileRequest dto = CreateProfileRequest.builder()
                .id(userId)
                .firstName(req.getFirstName())
                .lastName(req.getLastName())
                .phoneNumber(req.getPhoneNumber())
                .profilePicture(req.getProfilePicture())
                .birthDate(req.getBirthDate())
                .build();
        log.info("id from here: {}", dto.getId());
        profileClient.createProfile(dto);
    }
}
