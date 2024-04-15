package dedeirwanto.queue.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "backgrounds")
public class Background {
    @Id
    private String id;

    private String name;

    @Lob
    @Column(length = 52428800)
    private byte[] data;

    private String type;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "application_setting_id", referencedColumnName = "id")
    private ApplicationSetting applicationSetting;
}
