package integration;

import dev.zbib.userservice.enums.UserRoles;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserStatusResponse {

    private Long id;
    private UserRoles role;


}
