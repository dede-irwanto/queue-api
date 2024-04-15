package dedeirwanto.queue.repository;

import dedeirwanto.queue.entity.Queue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface QueueRepository extends JpaRepository<Queue, String> {
    // cek queue today
    boolean existsByCreatedAtAndNumberLikeOrderByPrintedAtDesc(LocalDate today, String numberLike);

    // print queue
    Queue findFirstByCreatedAtAndNumberLikeOrderByPrintedAtDesc(LocalDate today, String numberLike);

    // call queue
    Queue findFirstByCreatedAtAndNumberLikeAndIsCalledIsFalseOrderByPrintedAtAsc(LocalDate today, String numberLike);

    // total queue
    Integer countByCreatedAtAndNumberLike(LocalDate today, String numberLike);

    // complete queue
    Integer countByCreatedAtAndNumberLikeAndIsCalledIsTrue(LocalDate today, String numberLike);

    // remaining queue
    Integer countByCreatedAtAndNumberLikeAndIsCalledIsFalse(LocalDate today, String numberLike);

    // get last queue by counter
    Queue findFirstByCreatedAtAndCounterAndIsCalledIsTrueOrderByPrintedAtDesc(LocalDate today, String counter);

    // get first call queue
    Queue findFirstByCreatedAtAndIsCalledIsTrueOrderByPrintedAtDesc(LocalDate today);

    // chart query by service type
    @Query(value = """
            SET @sql = NULL;
            SELECT GROUP_CONCAT(DISTINCT 'SUM(CASE WHEN service_type = "', service_type, '" THEN 1 else 0 END) AS "', service_type, '"') INTO @sql FROM queues;
            SET @sql = CONCAT('SELECT created_at, ', @sql, ' FROM queues GROUP BY created_at;');
            PREPARE stmt FROM @sql;
            EXECUTE stmt;
            """, nativeQuery = true)
    List<Object> getCharts();
}
