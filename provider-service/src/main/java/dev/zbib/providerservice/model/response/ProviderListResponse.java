package dev.zbib.providerservice.model.response;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class ProviderListResponse extends DetailsResponse {
    private String firstName;
    private String lastName;
    private String profilePicture;
}
