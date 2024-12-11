package dev.zbib.providerservice.controller;

import dev.zbib.providerservice.dto.request.CreateProviderRequest;
import dev.zbib.providerservice.dto.response.ProviderResponse;
import dev.zbib.providerservice.service.ProviderService;
import dev.zbib.providerservice.service.UserService;
import dev.zbib.shared.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/providers")
@RequiredArgsConstructor
public class ProviderController {

    private final ProviderService providerService;
    private final UserService userService;


//    @PostMapping
//    public ResponseEntity<String> createProvider(@RequestBody CreateProviderRequest provider) {
//        providerService.createProvider(provider);
//        return ResponseEntity.ok()
//                .body("Provider created");
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<ProviderResponse> getProviderBId(@PathVariable Long id) {
//        return ResponseEntity.ok(providerService.getProviderById(id));
//    }


    @GetMapping("/users/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        UserResponse res = userService.getUserById(id);
        return ResponseEntity.ok(res);
    }

}
