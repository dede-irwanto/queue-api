package dedeirwanto.queue.service;

import dedeirwanto.queue.dto.UserCreateRequestDTO;
import dedeirwanto.queue.dto.UserResponseDTO;
import dedeirwanto.queue.dto.UserUpdateRequestDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    UserResponseDTO addUser(UserCreateRequestDTO request);

    UserResponseDTO updateUser(String userId, UserUpdateRequestDTO request);

    UserResponseDTO updateCurrentUser(UserUpdateRequestDTO request);

    List<UserResponseDTO> getAllUser();

    UserResponseDTO getUser();

    void deleteUser(String userId);

    // Total user for dashboard service
    long operatorCount();
}
