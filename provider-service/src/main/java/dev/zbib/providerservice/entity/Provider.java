package dev.zbib.providerservice.entity;

import dev.zbib.shared.enums.ServiceType;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    private Long id;

    @NotNull(message = BaseException.Provider.BIO_REQUIRED)
    @Size(min = 10, max = 500, message = BaseException.Provider.BIO_SIZE)
    @Column(nullable = false)
    private String bio;

    @NotNull(message = BaseException.Provider.SERVICE_TYPE_REQUIRED)
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ServiceType serviceType;

    @DecimalMin(value = "0.0")
    @DecimalMax(value = "5.0")
    @Column(nullable = false)
    private Double rating;

    @Column(nullable = false)
    private boolean available;

    @DecimalMin(value = "0.0", message = BaseException.Provider.MIN_HOURLY_RATE)
    @Column(nullable = false)
    private Double hourlyRate;

    @NotNull(message = BaseException.Provider.SERVICE_AREA_REQUIRED)
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
