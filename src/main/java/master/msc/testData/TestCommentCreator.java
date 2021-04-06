package master.msc.testData;

import com.thedeanda.lorem.LoremIpsum;
import master.msc.model.Comment;
import master.msc.model.TaskCommentChange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;


@Component
public class TestCommentCreator extends TestDataCreator<Comment> {

    @Autowired
    private TestTaskCommentChangeCreator testTaskCommentChangeCreator;

    @Override
    public Comment doCreateEntity() {
        Comment comment = new Comment();

        List<TaskCommentChange> taskCommentChangeList = testTaskCommentChangeCreator.createCollection(2);
        comment.setCommentChanges(taskCommentChangeList);
        comment.setContent("CommentContent_ " + LoremIpsum.getInstance().getWords(10, 15));


        return comment;
    }
}

