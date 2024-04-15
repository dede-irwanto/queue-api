package dedeirwanto.queue.controller;

import dedeirwanto.queue.dto.LoginRequestDTO;
import dedeirwanto.queue.dto.TokenResponseDTO;
import dedeirwanto.queue.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private AuthService authService;


    @PostMapping("/login")
    public ResponseEntity<TokenResponseDTO> login(@Valid @RequestBody LoginRequestDTO request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/refresh-token/{token}")
    public ResponseEntity<TokenResponseDTO> refreshToken(@PathVariable String token) {
        return ResponseEntity.ok(authService.refreshToken(token));
    }

    @PostMapping("/logout")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
    public ResponseEntity<String> logout() {
        authService.logout();
        return ResponseEntity.ok().body("Logout successfully");
    }
}
