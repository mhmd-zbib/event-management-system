package dev.zbib.userservice.service;

import dev.zbib.shared.dto.EligibilityResponse;
import dev.zbib.shared.enums.AccountStatus;
import dev.zbib.shared.enums.UserRole;
import dev.zbib.userservice.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProviderService {

    private final UserService userService;

    public EligibilityResponse canBecomeProvider(Long id) {
        User user = userService.getUserById(id);
        List<String> reasons = new ArrayList<>();
        if (user.getRole() == UserRole.PROVIDER) reasons.add("You already are a provider");
        if (!user.isVerified()) reasons.add("Provider account is not verified");
        if (user.getAccountStatus() != AccountStatus.ACTIVE) reasons.add("Provider account is not active");
        return EligibilityResponse.builder()
                .eligible(reasons.isEmpty())
                .reasons(reasons)
                .build();
    }
}
