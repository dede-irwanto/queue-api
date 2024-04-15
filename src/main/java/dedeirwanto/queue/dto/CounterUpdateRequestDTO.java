package dedeirwanto.queue.dto;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CounterUpdateRequestDTO {
    @Size(min = 5, message = "Name min. 5 character")
    private String name;
    private String serviceTypeId;
}
