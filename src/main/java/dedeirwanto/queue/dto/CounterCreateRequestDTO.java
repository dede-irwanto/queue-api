package dedeirwanto.queue.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CounterCreateRequestDTO {
    @NotBlank(message = "Name must not blank!")
    @Size(min = 5, message = "Name min. 5 character")
    private String name;

    @NotBlank(message = "Service type id must not blank!")
    private String serviceTypeId;
}
