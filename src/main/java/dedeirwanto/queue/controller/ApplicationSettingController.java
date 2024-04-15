package dedeirwanto.queue.controller;

import dedeirwanto.queue.dto.ApplicationSettingCreateRequestDTO;
import dedeirwanto.queue.dto.ApplicationSettingResponseDTO;
import dedeirwanto.queue.dto.ApplicationSettingUpdateRequestDTO;
import dedeirwanto.queue.entity.Background;
import dedeirwanto.queue.entity.Logo;
import dedeirwanto.queue.service.ApplicationSettingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/application-settings")
public class ApplicationSettingController {

    @Autowired
    private ApplicationSettingService applicationSettingService;

    @PostMapping()
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<ApplicationSettingResponseDTO> addApplicationSetting(@Valid @RequestBody ApplicationSettingCreateRequestDTO request) {
        return ResponseEntity.created(URI.create("/application-settings")).body(applicationSettingService.addApplicationSetting(request));
    }

    @PatchMapping()
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<ApplicationSettingResponseDTO> updateApplicationSetting(@Valid @RequestBody ApplicationSettingUpdateRequestDTO request) {
        ApplicationSettingResponseDTO response = applicationSettingService.updateApplicationSetting(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping()
    public ResponseEntity<ApplicationSettingResponseDTO> getApplicationSetting() {
        ApplicationSettingResponseDTO response = applicationSettingService.getApplicationSetting();
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/logos")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> addLogo(@RequestParam("file") MultipartFile file) throws IOException {
        applicationSettingService.addLogo(file);
        return ResponseEntity.created(URI.create("/application-settings/logos")).body("Add logo successful.");
    }

    @PatchMapping("/logos")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> updateLogo(@RequestParam("file") MultipartFile file) throws IOException {
        applicationSettingService.updateLogo(file);
        return ResponseEntity.ok("Update logo successful.");
    }

    @GetMapping("/logos")
    public ResponseEntity<byte[]> getLogo() {
        Logo logo = applicationSettingService.getLogo();
        MediaType mediaType = (Objects.equals(logo.getType(), "image/png")) ? MediaType.IMAGE_PNG :
                MediaType.IMAGE_JPEG;
        return ResponseEntity.ok().contentType(mediaType).body(logo.getData());
    }

    @PostMapping("/backgrounds")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> addBackground(@RequestParam("file") MultipartFile file) throws IOException {
        applicationSettingService.addBackground(file);
        return ResponseEntity.created(URI.create("/application-settings/backgrounds")).body("Add background successful.");
    }

    @PatchMapping("/backgrounds")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> updateBackground(@RequestParam("file") MultipartFile file) throws IOException {
        applicationSettingService.updateBackground(file);
        return ResponseEntity.ok("Update background successful.");
    }

    @GetMapping("/backgrounds")
    public ResponseEntity<byte[]> getBackground() {
        Background background = applicationSettingService.getBackground();
        MediaType mediaType = (Objects.equals(background.getType(), "image/png")) ? MediaType.IMAGE_PNG :
                MediaType.IMAGE_JPEG;
        return ResponseEntity.ok().contentType(mediaType).body(background.getData());
    }
}
