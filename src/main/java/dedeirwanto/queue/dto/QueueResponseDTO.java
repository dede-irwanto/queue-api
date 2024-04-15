package dedeirwanto.queue.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QueueResponseDTO {
    private String number;
    private String serviceTypeDescription;
    private Long printedAt;
    private String counter;
}
