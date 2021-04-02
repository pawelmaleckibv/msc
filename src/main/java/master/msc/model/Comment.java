package master.msc.model;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "comments")
public class Comment extends MscBaseEntity {

    private String content;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    private Employment commentator;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    private Task task;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "comment_x_task_comment_change",
            joinColumns = {@JoinColumn(name = "comment_id")},
            inverseJoinColumns = {@JoinColumn(name = "task_comment_change_id")}
    )
    private List<TaskCommentChange> commentChanges;

    public Comment() {

    }

    public Employment getCommentator() {
        return commentator;
    }

    public void setCommentator(Employment commentator) {
        this.commentator = commentator;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public List<TaskCommentChange> getCommentChanges() {
        return commentChanges;
    }

    public void setCommentChanges(List<TaskCommentChange> commentChanges) {
        this.commentChanges = commentChanges;
    }
}
