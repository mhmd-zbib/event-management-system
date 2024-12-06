package dev.zbib.providerservice.entity;

import dev.zbib.shared.enums.ServiceType;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "providers")
public class Provider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "User ID cannot be null")
    @Column(unique = true, nullable = false)
    private Long userId;

    @NotNull(message = "Bio cannot be null")
    @Size(min = 10, max = 500)
    @Column(nullable = false)
    private String bio;

    @NotNull(message = "Service type cannot be null")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ServiceType serviceType;

    @DecimalMin(value = "0.0")
    @DecimalMax(value = "5.0")
    @Column(nullable = false)
    private Double rating;

    @Column(nullable = false)
    private boolean available;

    @DecimalMin(value = "0.0")
    @Column(nullable = false)
    private Double hourlyRate;

    @NotNull(message = "Service area cannot be null")
    @Size(min = 5, max = 100)
    @Column(nullable = false)
    private String serviceArea;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        rating = 0.0;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
