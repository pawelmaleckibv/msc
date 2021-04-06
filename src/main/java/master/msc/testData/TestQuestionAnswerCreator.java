package master.msc.testData;

import master.msc.model.QuestionAnswer;
import org.springframework.stereotype.Component;


@Component
public class TestQuestionAnswerCreator extends TestDataCreator<QuestionAnswer> {

    @Override
    public QuestionAnswer doCreateEntity() {
        QuestionAnswer questionAnswer = new QuestionAnswer();
        return questionAnswer;
    }

}
