package dev.zbib.bookingservice.client;

import org.springframework.stereotype.Component;

@Component
public class UserClientFallback implements UserClient {
    @Override
    public UserResponse getUserById(Long id) {
        return UserResponse.builder()
                .id(id)
                .active(false)
                .build();
    }

    @Override
    public UserResponse getUserDetails(Long id) {
        return UserResponse.builder()
                .id(id)
                .active(false)
                .build();
    }
} 