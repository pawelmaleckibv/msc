package master.msc.model;

import javax.persistence.*;

@Entity
@Table(name = "task_changes")
public class TaskChange extends MscBaseEntity {

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    private Employment author;

    public Employment getAuthor() {
        return author;
    }

    public void setAuthor(Employment author) {
        this.author = author;
    }
}
