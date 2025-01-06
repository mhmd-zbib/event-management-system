package dev.zbib.stockservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Stock {

    @Id
    private String id;

    private boolean isAvailable;

    private Integer totalCapacity;

    private Integer availableCapacity;

    private Integer thresholdCapacity;

}
