package dev.zbib.stockservice.dto;

import lombok.Data;

@Data
public class CreateStockRequest {

    private String id;

    private boolean isAvailable;

    private Integer totalCapacity;

    private Integer thresholdCapacity;

}
