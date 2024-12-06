package dev.zbib.providerservice.model.entity;

import dev.zbib.shared.enums.ServiceType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "providers", uniqueConstraints = @UniqueConstraint(columnNames = "userId"))
public class Provider {

    @Id
    @NotNull(message = "User ID cannot be null")
    private Long id;

    @NotNull(message = "Bio cannot be null")
    @Size(min = 10, max = 500, message = "Bio should be between 10 and 500 characters")
    @Column(nullable = false)
    private String bio;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Service type cannot be null")
    private ServiceType serviceType;

    @Min(value = 0, message = "Rating must be greater than or equal to 0")
    @Max(value = 5, message = "Rating must be less than or equal to 5")
    private double rating;

    @Column(nullable = false)
    private boolean available;

    @Min(value = 0, message = "Hourly rate must be greater than or equal to 0")
    private double hourlyRate;

    @NotNull(message = "Service area cannot be null")
    @Size(min = 5, max = 100, message = "Service area must be between 5 and 100 characters")
    private String serviceArea;

//    @NotNull(message = "Available hours cannot be null")
//    @Size(min = 5, max = 100, message = "Available hours must be between 5 and 100 characters")
//    private String availableHours;
}
