package master.msc.testData;

import master.msc.enums.TaskPriority;
import master.msc.enums.TaskState;
import master.msc.model.Comment;
import master.msc.model.Task;
import master.msc.model.TaskChange;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class TestTaskCreator extends TestDataCreator<Task> {

    @Override
    public Task doCreateEntity() {
        Task task = new Task();

        task.setTaskName("Task_" + getRandom().nextInt(100));
        task.setDescription("Description_" + getRandom().nextInt(100));
        task.setExplanation("Explanation_" + getRandom().nextInt(100));
        task.setTaskState(TaskState.OPEN);
        task.setTaskPriority(TaskPriority.A);
        task.setTaskNumber(getRandom().nextInt(10));
        task.setTaskCost(getRandom().nextInt(10));
        task.setComments(generateCommentList());
        task.setTaskChanges(generateTaskChangeList());
        task.setDeadline(LocalDateTime.now().plusMonths(getRandom().nextInt(12)));

        return task;
    }

    private List<Comment> generateCommentList() {
        Comment comment = new Comment();
        comment.setContent("CommentContent_" + getRandom().nextInt(10));
        List<Comment> commentsList = new ArrayList<>();
        commentsList.add(comment);
        return commentsList;
    }

    private List<TaskChange> generateTaskChangeList() {
        TaskChange taskChange = new TaskChange();
        List<TaskChange> taskChangeList = new ArrayList<>();
        taskChangeList.add(taskChange);
        return taskChangeList;
    }

}

