package dedeirwanto.queue.controller;

import dedeirwanto.queue.dto.CounterCreateRequestDTO;
import dedeirwanto.queue.dto.CounterResponseDTO;
import dedeirwanto.queue.dto.CounterUpdateRequestDTO;
import dedeirwanto.queue.service.CounterService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/counters")
public class CounterController {
    @Autowired
    private CounterService counterService;

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<CounterResponseDTO> addServiceType(@Valid @RequestBody CounterCreateRequestDTO request) {
        return ResponseEntity.created(URI.create("/counters")).body(counterService.addCounter(request));
    }

    @PatchMapping("/{counterId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<CounterResponseDTO> updateServiceType(@PathVariable("counterId") String counterId, @Valid @RequestBody CounterUpdateRequestDTO request) {
        return ResponseEntity.ok(counterService.updateCounter(counterId, request));
    }

    @GetMapping
    public ResponseEntity<List<CounterResponseDTO>> getAllCounter() {
        return ResponseEntity.ok(counterService.getAllCounter());
    }

    @GetMapping("/{counterId}")
    public ResponseEntity<CounterResponseDTO> getCounter(@PathVariable("counterId") String counterId) {
        return ResponseEntity.ok(counterService.getCounter(counterId));
    }

    @DeleteMapping("/{counterId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> deleteCounter(@PathVariable("counterId") String counterId) {
        counterService.deleteCounter(counterId);
        return ResponseEntity.ok("Delete counter successful.");
    }
}
