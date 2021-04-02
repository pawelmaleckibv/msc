package master.msc.model;


import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "maturity_levels")
public class MaturityLevel extends MscBaseEntity {

    private String description;
    private Integer level;

    public MaturityLevel() {

    }

    public MaturityLevel(Integer level) {
        this.level = level;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
