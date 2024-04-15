package dedeirwanto.queue.controller;

import dedeirwanto.queue.dto.ChartResponseDTO;
import dedeirwanto.queue.repository.ServiceTypeRepository;
import dedeirwanto.queue.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/dashboard")
public class DashboardController {
    @Autowired
    private DashboardService dashboardService;
    @Autowired
    private ServiceTypeRepository serviceTypeRepository;

    @GetMapping("/operator-count")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Long> getOperatorCount() {
        return ResponseEntity.ok().body(dashboardService.operatorCount());
    }

    @GetMapping("/service-type-count")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Long> getServiceTypeCount() {
        return ResponseEntity.ok().body(dashboardService.serviceTypeCount());
    }

    @GetMapping("/counter-count")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Long> getCounterCount() {
        return ResponseEntity.ok().body(dashboardService.counterCount());
    }

    @GetMapping("/chart")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<ChartResponseDTO>> getCharts() {
        return ResponseEntity.ok().body(dashboardService.getCharts());
    }
}
