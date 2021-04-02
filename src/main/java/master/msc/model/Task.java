package master.msc.model;

import master.msc.enums.TaskPriority;
import master.msc.enums.TaskState;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tasks")
public class Task extends MscBaseEntity {

    @Column(length=1024)
    private String description;

    @Column(length=1024)
    private String explanation;

    @Column(length=1024)
    private String taskName;
    private int taskCost;
    private int taskNumber;
    private TaskState taskState;
    private TaskPriority taskPriority;
    private LocalDateTime deadline;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "task_x_person",
            joinColumns = {@JoinColumn(name = "task_id")},
            inverseJoinColumns = {@JoinColumn(name = "person_id")}
    )
    private List<Employment> taskExecutor = new ArrayList<>();

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    private Question question;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    private MaturityEvaluation maturityEvaluation;

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, mappedBy = "task")
    private List<Comment> comments = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "task_x_task_change",
            joinColumns = {@JoinColumn(name = "task_id")},
            inverseJoinColumns = {@JoinColumn(name = "task_change_id")}
    )
    private List<TaskChange> taskChanges = new ArrayList<>();

    public Task() {

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public int getTaskCost() {
        return taskCost;
    }

    public void setTaskCost(int taskCost) {
        this.taskCost = taskCost;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public List<Employment> getTaskExecutor() {
        return taskExecutor;
    }

    public void setTaskExecutor(List<Employment> taskExecutor) {
        this.taskExecutor = taskExecutor;
    }

    public void setTaskNumber(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    public Integer getTaskNumber() {
        return taskNumber;
    }

    public void setTaskNumber(Integer taskNumber) {
        this.taskNumber = taskNumber;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public TaskState getTaskState() {
        return taskState;
    }

    public void setTaskState(TaskState taskState) {
        this.taskState = taskState;
    }

    public MaturityEvaluation getMaturityEvaluation() {
        return maturityEvaluation;
    }

    public void setMaturityEvaluation(MaturityEvaluation maturityEvaluation) {
        this.maturityEvaluation = maturityEvaluation;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<TaskChange> getTaskChanges() {
        return taskChanges;
    }

    public void setTaskChanges(List<TaskChange> taskChanges) {
        this.taskChanges = taskChanges;
    }

    public TaskPriority getTaskPriority() {
        return taskPriority;
    }

    public void setTaskPriority(TaskPriority taskPriority) {
        this.taskPriority = taskPriority;
    }
}
