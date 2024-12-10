package dev.zbib.shared.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Address {
    private String street;
    private String city;
    private String postalCode;
    private String country;
}