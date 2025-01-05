package dev.zbib.listingservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "listings")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Listing {

    @Id
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
