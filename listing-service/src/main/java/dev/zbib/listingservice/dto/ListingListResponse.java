package dev.zbib.listingservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ListingListResponse {

    private UUID id;

    private String name;

    private BigDecimal price;

    private Integer stock;

    private boolean available;

    private String category;
}
