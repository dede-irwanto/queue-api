package dedeirwanto.queue.controller;

import dedeirwanto.queue.dto.UserCreateRequestDTO;
import dedeirwanto.queue.dto.UserResponseDTO;
import dedeirwanto.queue.dto.UserUpdateRequestDTO;
import dedeirwanto.queue.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping()
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<UserResponseDTO> addUser(@Valid @RequestBody UserCreateRequestDTO request) {
        return ResponseEntity.created(URI.create("/users")).body(userService.addUser(request));
    }

    @PatchMapping("/{userId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable("userId") String userId, @Valid @RequestBody UserUpdateRequestDTO request) {
        UserResponseDTO response = userService.updateUser(userId, request);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/current")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
    public ResponseEntity<UserResponseDTO> updateCurrentUser(@Valid @RequestBody UserUpdateRequestDTO request) {
        UserResponseDTO response = userService.updateCurrentUser(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping()
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<UserResponseDTO>> getAllUser() {
        List<UserResponseDTO> response = userService.getAllUser();
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/current")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
    public ResponseEntity<UserResponseDTO> getUser() {
        UserResponseDTO response = userService.getUser();
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{userId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> deleteUser(@PathVariable("userId") String userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok().body("Remove user successfully");
    }
}
