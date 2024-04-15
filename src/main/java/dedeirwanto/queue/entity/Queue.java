package dedeirwanto.queue.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "queues")
public class Queue {
    @Id
    private String id;

    private String number;

    @Column(name = "service_type")
    private String serviceType;

    @Column(name = "service_type_description")
    private String serviceTypeDescription;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @Column(name = "printed_at")
    private Long printedAt;

    @Column(name = "is_called")
    private Boolean isCalled;

    private String counter;
}
