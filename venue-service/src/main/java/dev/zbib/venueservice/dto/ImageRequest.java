package dev.zbib.venueservice.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ImageRequest {
    private UUID entityId;
    private String url;
    private int order;
    private int size;
    private int width;
    private int height;
}