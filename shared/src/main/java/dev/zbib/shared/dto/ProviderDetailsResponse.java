package dev.zbib.shared.dto;

import dev.zbib.shared.enums.AccountStatus;
import dev.zbib.shared.enums.ServiceType;
import lombok.Data;

@Data
public class ProviderDetailsResponse {
    private Long id;
    private String name;
    private String email;
    private ServiceType serviceType;
    private AccountStatus status;
    private boolean verified;
    private String description;
    private String location;
    private double rating;
} 