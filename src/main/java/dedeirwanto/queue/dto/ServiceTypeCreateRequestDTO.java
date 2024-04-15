package dedeirwanto.queue.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ServiceTypeCreateRequestDTO {
    @NotBlank(message = "Name must not blank")
    @Size(min = 5, message = "Name min. 5 character!")
    private String name;

    @NotBlank(message = "Description must not blank!")
    @Size(min = 10, message = "Description min. 10 character!")
    private String description;

    @NotBlank(message = "Code must not blank!")
    @Pattern(regexp = "^[A-Z]$", message = "Please choose 1 character from A to Z!")
    private String code;
}
