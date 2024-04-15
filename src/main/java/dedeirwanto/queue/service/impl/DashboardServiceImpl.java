package dedeirwanto.queue.service.impl;

import dedeirwanto.queue.service.*;
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
    private QueueService queueService;

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
    public List<Object> getCharts() {
        return queueService.getCharts();
    }
}
