package dev.zbib.providerservice.controller;

import dev.zbib.providerservice.dto.request.CreateProviderRequest;
import dev.zbib.providerservice.dto.request.ProviderFilterRequest;
import dev.zbib.providerservice.dto.response.DetailsResponse;
import dev.zbib.providerservice.dto.response.ProviderListResponse;
import dev.zbib.providerservice.dto.response.ProviderResponse;
import dev.zbib.providerservice.service.ProviderService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/providers")
@RequiredArgsConstructor
public class ProviderController {

    private final RabbitTemplate rabbitTemplate;
    private final Queue notificationQueue;
    private final ProviderService providerService;

    @PostMapping
    public ResponseEntity<DetailsResponse> createProvider(@RequestBody CreateProviderRequest request) {
        DetailsResponse response = providerService.createProvider(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProviderResponse> getProviderById(@PathVariable Long id) {
        ProviderResponse response = providerService.getProviderById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Page<ProviderListResponse>> getProviderList(
            @ModelAttribute ProviderFilterRequest providerFilterRequest,
            Pageable pageable) {
        Page<ProviderListResponse> providerDetails = providerService.getProviderList(providerFilterRequest, pageable);
        return new ResponseEntity<>(providerDetails, HttpStatus.OK);
    }

    @GetMapping("/test")
    public String test() {
        rabbitTemplate.convertAndSend(notificationQueue.getName(), "TESTING WENT WELL");
        return "Message sent";
    }
}
