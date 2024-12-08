package dev.zbib.providerservice.service;

import dev.zbib.providerservice.client.UserClient;
import dev.zbib.shared.dto.UserResponse;
import dev.zbib.shared.enums.UserRoles;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private UserClient userClient;

    public void setUserRole(
            Long id,
            UserRoles role) {
        try {
            userClient.setUserRole(id, role);
        } catch (FeignException.NotFound e) {
            throw new ResourceNotFoundException(BaseException.Provider.NOT_FOUND);
        }
    }

    public UserResponse getUser(Long id) {
        try {
            return userClient.getUser(id);
        } catch (FeignException.NotFound e) {
            throw new ResourceNotFoundException(BaseException.Provider.NOT_FOUND);
        }
    }
}
