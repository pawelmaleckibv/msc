package master.msc.model;

import com.blueveery.core.model.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
@AttributeOverrides(value = @AttributeOverride(name = "id", column = @Column(columnDefinition = "VARCHAR(36)")))
public class MscBaseEntity extends BaseEntity implements Serializable {

    @Version
    private int version;

    private LocalDateTime timestamp = LocalDateTime.now();

    public MscBaseEntity() {
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public int getVersion() {
        return version;
    }

    @PrePersist
    @PreUpdate
    public void updateTimestamp() {
        timestamp = LocalDateTime.now();
    }
}
