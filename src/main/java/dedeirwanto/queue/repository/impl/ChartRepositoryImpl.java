package dedeirwanto.queue.repository.impl;

import dedeirwanto.queue.dto.ChartResponseDTO;
import dedeirwanto.queue.repository.ChartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ChartRepositoryImpl implements ChartRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<ChartResponseDTO> getCharts() {
        String sql = "SET @sql = NULL;" +
                "SELECT GROUP_CONCAT(DISTINCT 'SUM(CASE WHEN service_type = \"', service_type, '\" THEN 1 else 0 END) AS \"', service_type, '\"') INTO @sql FROM queues;" +
                "SET @sql = CONCAT('SELECT created_at, ', @sql, ' FROM queues GROUP BY created_at;');" +
                "PREPARE stmt FROM @sql;" +
                "EXECUTE stmt;";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            ChartResponseDTO chartResponseDTO = new ChartResponseDTO();
            rs.getDate("created_at");
            rs.getString("service_type");
            rs.getLong("count");
            return chartResponseDTO;
        });
    }
}
