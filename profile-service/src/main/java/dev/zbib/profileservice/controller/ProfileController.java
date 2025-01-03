package dev.zbib.profileservice.controller;

import dev.zbib.profileservice.dto.request.CreateProfileDTO;
import dev.zbib.profileservice.dto.response.ProfileListResponse;
import dev.zbib.profileservice.dto.response.UserResponse;
import dev.zbib.profileservice.service.ProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profiles")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @PostMapping
    public ResponseEntity<UserResponse> createProfile(@Valid @RequestBody CreateProfileDTO request) {
        UserResponse response = profileService.createProfile(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getProfileById(@PathVariable Long id) {
        UserResponse response = profileService.getProfileResponseById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<ProfileListResponse>> getProfilesByIds(@RequestParam List<Long> ids) {
        List<ProfileListResponse> responses = profileService.getProfileListByIds(ids);
        return ResponseEntity.ok(responses);
    }
}
