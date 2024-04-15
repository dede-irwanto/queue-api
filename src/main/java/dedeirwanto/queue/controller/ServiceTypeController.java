package dedeirwanto.queue.controller;

import dedeirwanto.queue.dto.ServiceTypeCreateRequestDTO;
import dedeirwanto.queue.dto.ServiceTypeResponseDTO;
import dedeirwanto.queue.dto.ServiceTypeUpdateRequestDTO;
import dedeirwanto.queue.service.ServiceTypeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/service-types")
public class ServiceTypeController {
    @Autowired
    private ServiceTypeService serviceTypeService;

    @PostMapping()
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<ServiceTypeResponseDTO> addServiceType(@Valid @RequestBody ServiceTypeCreateRequestDTO request) {
        return ResponseEntity.created(URI.create("/service-types")).body(serviceTypeService.addServiceType(request));
    }

    @PatchMapping("/{serviceTypeId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<ServiceTypeResponseDTO> addServiceType(@PathVariable("serviceTypeId") String serviceTypeId, @Valid @RequestBody ServiceTypeUpdateRequestDTO request) {
        ServiceTypeResponseDTO serviceTypeResponseDTO = serviceTypeService.updateServiceType(serviceTypeId, request);
        return ResponseEntity.ok(serviceTypeResponseDTO);
    }

    @GetMapping()
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<List<ServiceTypeResponseDTO>> getAllServiceType() {
        List<ServiceTypeResponseDTO> list = serviceTypeService.getAllServiceType();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{serviceTypeId}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<ServiceTypeResponseDTO> getServiceType(@PathVariable("serviceTypeId") String serviceTypeId) {
        ServiceTypeResponseDTO serviceType = serviceTypeService.getServiceType(serviceTypeId);
        return ResponseEntity.ok(serviceType);
    }

    @DeleteMapping("/{serviceTypeId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> deleteServiceType(@PathVariable("serviceTypeId") String serviceTypeId) {
        serviceTypeService.deleteServiceType(serviceTypeId);
        return ResponseEntity.ok("Delete service type successful.");
    }
}
