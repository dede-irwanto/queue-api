package dedeirwanto.queue.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Valid
public class ApplicationSettingUpdateRequestDTO {
    @Size(min = 5, max = 100, message = "Institute name must be between 5 and 100")
    private String instituteName;

    @Size(min = 10, max = 500, message = "Footer must be between 10 and 500")
    private String footer;
}
