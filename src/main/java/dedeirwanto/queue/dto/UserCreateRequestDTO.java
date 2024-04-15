package dedeirwanto.queue.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserCreateRequestDTO {
    @NotBlank(message = "Username must not blank")
    @Size(min = 3, message = "Username min. 3 character")
    private String username;

    @NotBlank(message = "Password must not blank")
    @Size(min = 5, message = "Password min. 5 character")
    private String password;

    @NotBlank(message = "Full name must not blank")
    @Size(min = 3, message = "Full name min. 3 character")
    private String fullName;
    
    @NotBlank(message = "Role id must not blank")
    private String roleId;
}
