package dev.zbib.bookingservice.service;

import dev.zbib.bookingservice.client.UserClient;
import dev.zbib.bookingservice.integration.UserStatusResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserClient userClient;

    public void verifyUserStatus(Long id) {
        UserStatusResponse userStatus = userClient.getUserStatus(id);
        if (userStatus.getStatus()
                .toLowerCase() != "active") throw new IllegalArgumentException("Throw related error");
        if (userStatus.isBlocked()) throw new IllegalArgumentException("Throw related error");
        if (!userStatus.isVerified()) throw new IllegalArgumentException("Throw related error");
    }

    public void verifyProviderStatus(Long id) {
        UserStatusResponse providerStatus = userClient.getUserStatus(id);
        if (providerStatus.getStatus()
                .toLowerCase() != "active" || providerStatus.isBlocked() || !providerStatus.isVerified())
            throw new IllegalArgumentException("Throw related error");
        if (providerStatus.getRole()
                .toLowerCase() != "provider") throw new IllegalArgumentException("Throw related error");
    }
}
