package dev.zbib.userservice.client;

import dev.zbib.userservice.dto.CreateProfileDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "user-service")
public interface ProfileClient {

    @PostMapping("/users")
    void createProfile(@RequestBody CreateProfileDTO dto);

}
