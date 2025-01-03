package dev.zbib.profileservice.service;

import dev.zbib.profileservice.entity.Profile;
import dev.zbib.shared.dto.EligibilityResponse;
import dev.zbib.shared.enums.AccountStatus;
import dev.zbib.shared.enums.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final ProfileService profileService;

    public EligibilityResponse canBook(Long id) {
        Profile profile = profileService.getUserById(id);
        List<String> reasons = new ArrayList<>();
        if (!profile.isVerified()) reasons.add("Your account is not verified");
        if (profile.getAccountStatus() != AccountStatus.ACTIVE) reasons.add("Your account is not active");
        if (profile.getRole() == UserRole.PROVIDER) reasons.add("Providers can't book");
        return EligibilityResponse.builder()
                .eligible(reasons.isEmpty())
                .reasons(reasons)
                .build();
    }


    public EligibilityResponse canBeBooked(Long id) {
        Profile profile = profileService.getUserById(id);
        List<String> reasons = new ArrayList<>();
        if (profile.getRole() == UserRole.CUSTOMER) reasons.add("User is not a provider");
        if (!profile.isVerified()) reasons.add("Provider account is not verified");
        if (profile.getAccountStatus() != AccountStatus.ACTIVE) reasons.add("Provider account is not active");

        return EligibilityResponse.builder()
                .eligible(reasons.isEmpty())
                .reasons(reasons)
                .build();
    }
}
