package dev.zbib.providerservice.service;

import dev.zbib.providerservice.client.UserClient;
import dev.zbib.shared.dto.UserResponse;
import dev.zbib.shared.dto.AppException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class UserService {
    private final UserClient userClient;

    public UserResponse getUserById(Long id) {
        try {
            log.debug("Fetching user with id: {}", id);
            return userClient.getUser(id);
        } catch (AppException ex) {
            log.error("Error fetching user with id {}: {}", id, ex.getMessage());
            throw ex;
        } catch (Exception ex) {
            log.error("Unexpected error fetching user with id {}", id, ex);
            throw ex;
        }
    }
}
