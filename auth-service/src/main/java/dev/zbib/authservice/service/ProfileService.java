package dev.zbib.authservice.service;

import dev.zbib.authservice.client.ProfileClient;
import dev.zbib.authservice.dto.CreateProfileDTO;
import dev.zbib.authservice.dto.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileClient profileClient;

    public void createProfile(RegisterRequest req) {
        CreateProfileDTO dto = CreateProfileDTO.builder()
                .firstName(req.getFirstName())
                .lastName(req.getLastName())
                .phoneNumber(req.getPhoneNumber())
                .build();
        profileClient.createProfile(dto);
    }
}
