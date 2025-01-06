package dev.zbib.stockservice.dto;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StockResponse {

    @Id
    private String id;

    private boolean isAvailable;

    private Integer totalCapacity;

    private Integer availableCapacity;

}
