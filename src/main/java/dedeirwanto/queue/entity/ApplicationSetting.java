package dedeirwanto.queue.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "application_settings")
public class ApplicationSetting {
    @Id
    private String id;

    @Column(name = "institute_name")
    private String instituteName;

    private String footer;

    @OneToOne(mappedBy = "applicationSetting")
    private Logo logo;
}
