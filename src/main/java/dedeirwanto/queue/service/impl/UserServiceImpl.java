package dedeirwanto.queue.service.impl;

import dedeirwanto.queue.dto.UserCreateRequestDTO;
import dedeirwanto.queue.dto.UserResponseDTO;
import dedeirwanto.queue.dto.UserUpdateRequestDTO;
import dedeirwanto.queue.entity.Role;
import dedeirwanto.queue.entity.User;
import dedeirwanto.queue.exception.BadRequestException;
import dedeirwanto.queue.exception.ResourceNotFoundException;
import dedeirwanto.queue.repository.UserRepository;
import dedeirwanto.queue.service.AuthService;
import dedeirwanto.queue.service.RoleService;
import dedeirwanto.queue.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Primary
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleService roleService;
    @Autowired
    private AuthService authService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private UserResponseDTO toUserResponseDTO(String id,
                                              String username,
                                              String fullName,
                                              String role) {
        return UserResponseDTO.builder()
                .id(id)
                .username(username)
                .fullName(fullName)
                .role(role)
                .build();
    }

    @Override
    public UserResponseDTO addUser(UserCreateRequestDTO request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new BadRequestException("Username already exists");
        }

        Role role = roleService.get(request.getRoleId());
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setFullName(request.getFullName());
        user.setRole(role);
        userRepository.save(user);

        return toUserResponseDTO(user.getId(), user.getUsername(), user.getFullName(), user.getRole().getName());
    }

    @Override
    public UserResponseDTO updateUser(String userId, UserUpdateRequestDTO request) {
        User update = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Invalid user!"));
        String fullName = (request.getFullName() == null) ? update.getFullName() : request.getFullName();
        String password = (request.getPassword() == null) ? update.getPassword() : passwordEncoder.encode(request.getPassword());
        Role role = (request.getRoleId() == null) ? roleService.get(update.getRole().getId()) : roleService.get(request.getRoleId());
        update.setFullName(fullName);
        update.setPassword(password);
        update.setRole(role);
        userRepository.save(update);

        return toUserResponseDTO(update.getId(), update.getUsername(), update.getFullName(), update.getRole().getName());
    }

    @Override
    public UserResponseDTO updateCurrentUser(UserUpdateRequestDTO request) {
        User update = userRepository.findByUsername(authService.loggedinUser()).orElseThrow(() -> new BadRequestException("Invalid user!"));

        String fullName = (request.getFullName() == null) ? update.getFullName() : request.getFullName();

        String password;

        if (request.getPassword() == null && request.getCurrentPassword() == null) {
            password = update.getPassword();
        } else {
            if (!passwordEncoder.matches(request.getCurrentPassword(),
                    update.getPassword())) {
                throw new BadRequestException("Current password did not match!");
            }
            password = passwordEncoder.encode(request.getPassword());
        }

        update.setFullName(fullName);
        update.setPassword(password);
        userRepository.save(update);

        return toUserResponseDTO(update.getId(), update.getUsername(), update.getFullName(), update.getRole().getName());
    }

    @Override
    public List<UserResponseDTO> getAllUser() {
        Role role = roleService.findByName("ROLE_USER");
        List<User> users = userRepository.findByRole(role);
        return users.stream().map((user) -> {
            UserResponseDTO dto = new UserResponseDTO();
            dto.setId(user.getId());
            dto.setFullName(user.getFullName());
            dto.setUsername(user.getUsername());
            dto.setRole(user.getRole().getName());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public UserResponseDTO getUser() {
        String username = authService.loggedinUser();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("Invalid user!"));
        return toUserResponseDTO(user.getId(), user.getUsername(), user.getFullName(), user.getRole().getName());
    }

    @Override
    public void deleteUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Invalid user!"));
        userRepository.delete(user);
    }

    @Override
    public long operatorCount() {
        return userRepository.count();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Invalid username"));
    }
}
