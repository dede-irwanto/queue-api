package dedeirwanto.queue.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationSettingResponseDTO {
    private String id;
    private String instituteName;
    private String footer;
}
