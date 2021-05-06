package master.msc.model;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import java.util.List;

@NamedNativeQuery(
        name = "QuestionaryDto",
        query = "SELECT q.name as questionaryName, bu.name as businessUnitName, q2.name as questionName, a.answerContent as answers\n" +
                "FROM questionaries q\n" +
                "         JOIN questionary_x_business_unit qxbu on q.id = qxbu.questionary_id\n" +
                "         JOIN business_units bu on bu.id = qxbu.business_unit_id\n" +
                "         JOIN questionary_x_question qxq on q.id = qxq.questionary_id\n" +
                "         JOIN questions q2 on qxq.question_id = q2.id\n" +
                "         JOIN answers a on qxq.question_id = a.question_id",
        resultSetMapping = "QuestionaryDto"
)
@SqlResultSetMapping(
        name = "QuestionaryDto",
        classes = @ConstructorResult(
                targetClass = QuestionaryDto.class,
                columns = {
                        @ColumnResult(name = "questionaryName"),
                        @ColumnResult(name = "businessUnitName"),
                        @ColumnResult(name = "questionName"),
                        @ColumnResult(name = "answers")
                }
        )
)
public class QuestionaryDto {
    private String questionaryName;
    private List<String> businessUnitName;
    private List<String> questionName;
    private List<String> answers;
    public static final String QUERY_NAME = "dupa";

    public QuestionaryDto() {
    }

    public QuestionaryDto(String questionaryName, List<String> businessUnitName, List<String> questionName, List<String> answers) {
        this.questionaryName = questionaryName;
        this.businessUnitName = businessUnitName;
        this.questionName = questionName;
        this.answers = answers;
    }

    public String getQuestionaryName() {
        return questionaryName;
    }

    public void setQuestionaryName(String questionaryName) {
        this.questionaryName = questionaryName;
    }

    public List<String> getBusinessUnitName() {
        return businessUnitName;
    }

    public void setBusinessUnitName(List<String> businessUnitName) {
        this.businessUnitName = businessUnitName;
    }

    public List<String> getQuestionName() {
        return questionName;
    }

    public void setQuestionName(List<String> questionName) {
        this.questionName = questionName;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }
}
