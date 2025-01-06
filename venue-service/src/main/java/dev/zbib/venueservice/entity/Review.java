package dev.zbib.venueservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "reviews")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Review {

    @Id
    private String id;

    private String userId;

    private String comment;

    private Integer rating;

    private String venueId;

}
