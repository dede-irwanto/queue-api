package dedeirwanto.queue.repository;

import dedeirwanto.queue.entity.Role;
import dedeirwanto.queue.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUsername(String username);

    Optional<User> findFirstByToken(String token);

    List<User> findByRole(Role role);

    boolean existsByUsername(String username);
}
