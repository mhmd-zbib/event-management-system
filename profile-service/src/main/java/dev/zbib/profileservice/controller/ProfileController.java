package dev.zbib.profileservice.controller;

import dev.zbib.profileservice.dto.CreateProfileRequest;
import dev.zbib.profileservice.dto.ProfileListResponse;
import dev.zbib.profileservice.dto.ProfileResponse;
import dev.zbib.profileservice.service.ProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/profiles")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @PostMapping
    public ResponseEntity<ProfileResponse> createProfile(@RequestBody CreateProfileRequest request) {
        return ResponseEntity.ok(profileService.createProfile(request));
    }

    @GetMapping("/me")
    public ResponseEntity<ProfileResponse> getProfile(@AuthenticationPrincipal OAuth2User jwt) {
        String userId = jwt.getAttribute("sub");
        log.info("user id: {} ", userId);
        return ResponseEntity.ok(profileService.getProfileById(userId));
    }

    @GetMapping
    public ResponseEntity<List<ProfileListResponse>> getProfilesByIds(@RequestParam List<String> ids) {
        return ResponseEntity.ok(profileService.getProfileListByIds(ids));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfileResponse> getProfileById(@PathVariable String id) {
        return ResponseEntity.ok(profileService.getProfileById(id));
    }
}
