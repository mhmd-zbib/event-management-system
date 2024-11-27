package dev.zbib.providerservice.service;

import dev.zbib.providerservice.model.entity.Provider;
import dev.zbib.providerservice.model.response.UserListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserClient userClient;

    public List<UserListResponse> getUserDetailsForProviders(List<Provider> providers) {
        List<Long> userIds = providers.stream()
                .map(Provider::getId)
                .collect(Collectors.toList());
        return userClient.getUsersByIds(userIds);
    }
}
