package dedeirwanto.queue.repository;

import dedeirwanto.queue.dto.ChartResponseDTO;

import java.util.List;

public interface ChartRepository {
    List<ChartResponseDTO> getCharts();
}
