package dev.zbib.shared.dto;

import lombok.Data;
import org.springframework.data.domain.Sort;

@Data
public class PageRequest {
    private int page = 0;
    private int size = 10;
    private String sortBy = "id";
    private Sort.Direction direction = Sort.Direction.ASC;

    public org.springframework.data.domain.PageRequest toSpringPageRequest() {
        return org.springframework.data.domain.PageRequest.of(
            page, 
            size, 
            direction, 
            sortBy
        );
    }
} 