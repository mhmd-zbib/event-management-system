package dev.zbib.listingservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListingResponse {

    private UUID id;

    private String name;

    private String description;

    private BigDecimal price;

    private Integer reservedStock;

    private String userId;

    private Integer stock;

    private boolean available;

    private String category;

}
