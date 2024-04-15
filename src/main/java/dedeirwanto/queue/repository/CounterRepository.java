package dedeirwanto.queue.repository;

import dedeirwanto.queue.entity.Counter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CounterRepository extends JpaRepository<Counter, String> {
    boolean existsByName(String name);
}
