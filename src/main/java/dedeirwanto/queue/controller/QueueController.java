package dedeirwanto.queue.controller;

import dedeirwanto.queue.dto.QueueResponseDTO;
import dedeirwanto.queue.service.QueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/queues")
public class QueueController {
    @Autowired
    private QueueService queueService;

    @GetMapping("/print/{serviceTypeId}")
    public ResponseEntity<QueueResponseDTO> queuePrint(@PathVariable("serviceTypeId") String serviceTypeId) {
        return ResponseEntity.ok(queueService.queuePrint(serviceTypeId));
    }

    @PatchMapping("/call/{counterId}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<QueueResponseDTO> queueCall(@PathVariable("counterId") String counterId) {
        return ResponseEntity.ok(queueService.queueCall(counterId));
    }

    @GetMapping("/{serviceTypeId}/total")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<Integer> queueTotal(@PathVariable("serviceTypeId") String serviceTypeId) {
        return ResponseEntity.ok(queueService.queueTotal(serviceTypeId));
    }

    @GetMapping("/{serviceTypeId}/remaining")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<Integer> queueRemaining(@PathVariable("serviceTypeId") String serviceTypeId) {
        return ResponseEntity.ok(queueService.queueRemaining(serviceTypeId));
    }

    @GetMapping("/{serviceTypeId}/completed")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<Integer> queueCompleted(@PathVariable("serviceTypeId") String serviceTypeId) {
        return ResponseEntity.ok(queueService.queueCompleted(serviceTypeId));
    }

    @GetMapping("/counter/{counterId}")
    public ResponseEntity<QueueResponseDTO> getQueueByCounter(@PathVariable("counterId") String counterId) {
        return ResponseEntity.ok(queueService.getLastQueueByCounter(counterId));
    }

    @GetMapping("/called")
    public ResponseEntity<QueueResponseDTO> getQueueCalled() {
        return ResponseEntity.ok(queueService.getQueueCalled());
    }
}
