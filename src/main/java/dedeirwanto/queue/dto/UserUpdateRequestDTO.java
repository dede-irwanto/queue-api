package dedeirwanto.queue.dto;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserUpdateRequestDTO {
    @Size(min = 5, message = "Password min. 5 character")
    private String password;

    private String currentPassword;

    @Size(min = 3, message = "Full name min. 3 character")
    private String fullName;
    private String roleId;
}
