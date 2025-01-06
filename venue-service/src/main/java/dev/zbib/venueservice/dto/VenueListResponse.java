package dev.zbib.venueservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VenueListResponse {

    private String id;

    private String name;

    private Double price;

    private Integer stock;

    private boolean available;

    private String category;
}
