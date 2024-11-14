package dev.zbib.providerservice.model.entity;

import dev.zbib.providerservice.model.enums.ServiceType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Provider {

    @Id
    @GeneratedValue
    private Long id;

    private Long userId;

    private String bio;

    private ServiceType serviceType;

    private double rating;

    private boolean available;

    private double hourlyRate;

    private String serviceArea;

    private String availableHours;

}
