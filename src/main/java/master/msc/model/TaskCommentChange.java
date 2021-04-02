package master.msc.model;

import javax.persistence.Entity;

@Entity
public class TaskCommentChange extends TaskChange {
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
