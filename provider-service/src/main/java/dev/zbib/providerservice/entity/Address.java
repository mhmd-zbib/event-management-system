package dev.zbib.providerservice.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Address {
    private String street;
    private String city;
    private String postalCode;
    private String country;
}