package dedeirwanto.queue.service.impl;

import dedeirwanto.queue.dto.ChartResponseDTO;
import dedeirwanto.queue.repository.QueueRepository;
import dedeirwanto.queue.service.CounterService;
import dedeirwanto.queue.service.DashboardService;
import dedeirwanto.queue.service.ServiceTypeService;
import dedeirwanto.queue.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardServiceImpl implements DashboardService {
    @Autowired
    private UserService userService;

    @Autowired
    private ServiceTypeService serviceTypeService;

    @Autowired
    private CounterService counterService;

    @Autowired
    private QueueRepository queueRepository;

    @Override
    public long operatorCount() {
        return userService.operatorCount();
    }

    @Override
    public long serviceTypeCount() {
        return serviceTypeService.serviceTypeCount();
    }

    @Override
    public long counterCount() {
        return counterService.counterCount();
    }

    @Override
    public List<ChartResponseDTO> getCharts() {
        return queueRepository.getCharts();
    }
}
