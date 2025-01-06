package dev.zbib.listingservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ListingResponse {

    private String id;

    private String name;

    private String description;

    private Double price;

    private Integer reservedStock;

    private String userId;

    private Integer stock;

    private boolean available;

    private String category;

}
