package dev.zbib.providerservice.client;

import dev.zbib.providerservice.dto.response.UserListResponse;
import dev.zbib.providerservice.dto.response.UserResponse;
import dev.zbib.shared.dto.EligibilityResponse;
import dev.zbib.shared.enums.UserRole;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "user-service")
public interface UserClient {

    @GetMapping("/users/{id}")
    UserResponse getUser(@PathVariable Long id);

    @PutMapping("/users/{id}/role")
    void setRole(
            @PathVariable Long id,
            @RequestBody UserRole role);

    @GetMapping("/users/{id}/eligibility/can-be-provider")
    EligibilityResponse canBeProvider(@PathVariable Long id);

    @GetMapping("/users")
    List<UserListResponse> getUsersById(@RequestParam List<Long> ids);
}
