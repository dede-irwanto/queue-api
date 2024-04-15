package dedeirwanto.queue.service.impl;

import dedeirwanto.queue.dto.LoginRequestDTO;
import dedeirwanto.queue.dto.TokenResponseDTO;
import dedeirwanto.queue.entity.User;
import dedeirwanto.queue.exception.BadRequestException;
import dedeirwanto.queue.exception.ResourceNotFoundException;
import dedeirwanto.queue.repository.UserRepository;
import dedeirwanto.queue.security.JwtService;
import dedeirwanto.queue.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public TokenResponseDTO login(LoginRequestDTO request) {
        User user = userRepository.findByUsername(request.getUsername()).orElseThrow(() -> new BadRequestException("Invalid username or password!"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadRequestException("Invalid username or password!");
        }

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        String token = jwtService.generateToken(request.getUsername());
        Long expiredAt = jwtService.extractExpiration(token).getTime();

        user.setToken(token);
        user.setExpiredAt(expiredAt);
        userRepository.save(user);

        return TokenResponseDTO.builder()
                .token(token)
                .expiredAt(expiredAt)
                .build();
    }

    @Override
    public TokenResponseDTO refreshToken(String token) {
        User user = userRepository.findFirstByToken(token).orElseThrow(() -> new ResourceNotFoundException("Token not found!"));
        String newToken = jwtService.generateToken(user.getUsername());
        Long expiredAt = jwtService.extractExpiration(token).getTime();

        user.setToken(newToken);
        user.setExpiredAt(expiredAt);
        userRepository.save(user);

        return TokenResponseDTO.builder()
                .token(newToken)
                .expiredAt(expiredAt)
                .build();
    }

    @Override
    public String loggedinUser() {
        SecurityContext ctx = SecurityContextHolder.getContext();
        return ctx.getAuthentication().getName();
    }

    @Override
    public void hasLoggedOut(String token) {
        userRepository.findFirstByToken(token).orElseThrow(() -> new BadRequestException("Invalid token!"));
    }

    @Override
    public void logout() {
        User user = userRepository.findByUsername(loggedinUser()).orElseThrow(() -> new ResourceNotFoundException("Invalid username!"));
        user.setToken(null);
        user.setExpiredAt(null);
        userRepository.save(user);
    }
}
