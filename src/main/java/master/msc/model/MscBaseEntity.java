package master.msc.model;

import com.blueveery.core.model.BaseEntity;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Version;
import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
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
