package dev.zbib.providerservice.dto.response;

import dev.zbib.shared.enums.AccountStatus;
import dev.zbib.shared.enums.ServiceType;
import dev.zbib.shared.enums.UserRoles;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public class ProviderResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private LocalDate birthDate;
    private String profilePicture;
    private UserRoles role;
    private AccountStatus status;
    private boolean isVerified;
    private boolean isBlocked;
    private String bio;
    private ServiceType serviceType;
    private double hourlyRate;
    private String serviceArea;
    private double rating;
    private boolean available;
}
