package dedeirwanto.queue.service;

import java.util.List;

public interface DashboardService {
    long operatorCount();

    long serviceTypeCount();

    long counterCount();

    List<Object> getCharts();
}
