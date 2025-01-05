package dev.zbib.listingservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ListingListResponse {

    private String id;

    private String name;

    private Double price;

    private Integer stock;

    private boolean available;

    private String category;
}
