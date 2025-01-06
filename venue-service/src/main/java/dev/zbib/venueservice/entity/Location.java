package dev.zbib.venueservice.entity;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Location {

    private String address;
    private String city;
    private String state;
    private String zip;
    private String country;
    private double latitude;
    private double longitude;
    private String url;

}
