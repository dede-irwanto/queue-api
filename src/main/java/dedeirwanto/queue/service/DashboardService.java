package dedeirwanto.queue.service;

import dedeirwanto.queue.dto.ChartResponseDTO;

import java.util.List;

public interface DashboardService {
    long operatorCount();

    long serviceTypeCount();

    long counterCount();

    List<ChartResponseDTO> getCharts();
}
