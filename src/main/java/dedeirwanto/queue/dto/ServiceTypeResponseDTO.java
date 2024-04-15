package dedeirwanto.queue.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceTypeResponseDTO {
    private String id;
    private String name;
    private String description;
    private String code;
}
