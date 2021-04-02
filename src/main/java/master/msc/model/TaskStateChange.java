package master.msc.model;

import master.msc.enums.TaskState;

import javax.persistence.Entity;


@Entity
public class TaskStateChange extends TaskChange {
    private TaskState state;

    public TaskState getState() {
        return state;
    }

    public void setState(TaskState state) {
        this.state = state;
    }
}
