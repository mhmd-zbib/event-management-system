package dev.zbib.providerservice.client;

import dev.zbib.shared.enums.UserRoles;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "user-service")
public interface UserClient {
    @PatchMapping("/users/{userId}/role")
    void updateUserRole(@PathVariable("userId") Long userId, @RequestParam UserRoles role);
    
    @GetMapping("/users/batch")
    List<UserDetailsResponse> getUserDetailsBatch(@RequestParam List<Long> userIds);
}
