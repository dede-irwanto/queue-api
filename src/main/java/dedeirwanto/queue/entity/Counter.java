package dedeirwanto.queue.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "counters")
public class Counter {
    @Id
    private String id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "service_type_id", referencedColumnName = "id")
    private ServiceType serviceType;
}
