package dedeirwanto.queue.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "service_types")
public class ServiceType {
    @Id
    private String id;
    private String name;
    private String description;
    private String code;
}
