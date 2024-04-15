package dedeirwanto.queue.dto;

import dedeirwanto.queue.entity.ServiceType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CounterResponseDTO {
    private String id;
    private String name;
    private ServiceType serviceType;
}
