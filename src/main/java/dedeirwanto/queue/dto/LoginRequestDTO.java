package dedeirwanto.queue.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDTO {
    @NotBlank(message = "Username must not blank")
    private String username;
    @NotBlank(message = "Password must not blank")
    private String password;
}
