package dev.zbib.providerservice.service;

import dev.zbib.providerservice.client.UserClient;
import dev.zbib.shared.dto.UserResponse;
import dev.zbib.shared.enums.UserRoles;
import dev.zbib.shared.exception.ProviderException;
import dev.zbib.shared.exception.UserException;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private UserClient userClient;


    public UserResponse getUserById(Long id) {
        UserResponse res = userClient.getUser(id);
        if (res == null) {
            throw UserException.notFound();
        }
        return res;
    }


    public void setUserRole(
            Long id,
            UserRoles role) {
        try {
            userClient.setUserRole(id, role);
        } catch (FeignException.NotFound e) {
            throw ProviderException.notFound();
        }
    }

    public UserResponse getUser(Long id) {
        try {
            return userClient.getUser(id);
        } catch (FeignException.NotFound e) {
            throw ProviderException.notFound();
        }
    }
}
