package dev.zbib.userservice.client;

import dev.zbib.userservice.dto.CreateProfileRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "profile-service")
public interface ProfileClient {

    @PostMapping
    void createProfile(@RequestBody CreateProfileRequest dto);

}
