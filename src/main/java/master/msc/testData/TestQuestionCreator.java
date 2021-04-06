package master.msc.testData;

import com.thedeanda.lorem.LoremIpsum;
import master.msc.enums.AnswerType;
import master.msc.enums.QuestionType;
import master.msc.model.Answer;
import master.msc.model.Question;
import org.springframework.stereotype.Component;

@Component
public class TestQuestionCreator extends TestDataCreator<Question> {


    @Override
    public Question doCreateEntity() {
        Question question = new Question();

        question.setContentOfTheQuestion(LoremIpsum.getInstance().getWords(15, 20));
        question.setAnswerType(AnswerType.ENUM);
        question.setQuestionType(QuestionType.MULTIPLE_SELECTION);
        for (int i = 0; i < 4; i++) {
            question.addAnswer(generateAnswer(question));
        }
        return question;
    }

    private Answer generateAnswer(Question question) {
        Answer answer = new Answer();
        answer.setAnswerContent("AnswerContent_" + getRandom().nextInt(100));
        answer.setQuestion(question);
//        answer.setAnswerType(AnswerType.ENUM);
        return answer;
    }

}
