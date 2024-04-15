package dedeirwanto.queue.service;

import dedeirwanto.queue.dto.LoginRequestDTO;
import dedeirwanto.queue.dto.TokenResponseDTO;

public interface AuthService {
    TokenResponseDTO login(LoginRequestDTO request);

    TokenResponseDTO refreshToken(String token);

    String loggedinUser();

    void hasLoggedOut(String token);

    void logout();
}
