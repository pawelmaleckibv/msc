package master.msc.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "system_configs")
public class SystemConfig extends MscBaseEntity {

    private String name;
    private String value;

    public SystemConfig() {
    }

    public SystemConfig(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
