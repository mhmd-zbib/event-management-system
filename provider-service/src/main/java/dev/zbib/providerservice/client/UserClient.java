package dev.zbib.providerservice.client;

import dev.zbib.shared.config.FeignConfig;
import dev.zbib.shared.dto.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
    name = "user-service",
    configuration = FeignConfig.class
)
public interface UserClient {
    @GetMapping("/users/{id}")
    UserResponse getUser(@PathVariable Long id);
}
