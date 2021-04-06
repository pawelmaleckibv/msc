package master.msc.testData;

import master.msc.model.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestAnswerCreator extends TestDataCreator<Answer> {

    @Autowired
    private TestQuestionCreator questionCreator;


    @Override
    public Answer doCreateEntity() {
        Answer answer = new Answer();
//        answer.setAnswerType(AnswerType.ENUM);
        answer.setAnswerContent("AnswerContent_" + getRandom().nextInt(10));
//        answer.setQuestion(questionCreator.createEntity());
        return answer;
    }
}
