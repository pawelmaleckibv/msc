package master.msc.testData;

import master.msc.model.TaskCommentChange;
import org.springframework.stereotype.Component;


@Component
public class TestTaskCommentChangeCreator extends TestDataCreator<TaskCommentChange>{

    @Override
    public TaskCommentChange doCreateEntity() {
        TaskCommentChange taskCommentChange = new TaskCommentChange();

        taskCommentChange.setContent("TaskCommentChange_" + getRandom().nextInt(100));

        return taskCommentChange;
    }


}
