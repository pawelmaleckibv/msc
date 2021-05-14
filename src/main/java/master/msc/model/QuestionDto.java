package master.msc.model;

import java.util.ArrayList;
import java.util.List;

public class QuestionDto  {
    public String questionName ;
    public List<String> answers  = new ArrayList<>();

    public QuestionDto() {
    }

    public QuestionDto(String questionName, List<String> answers) {
        this.questionName = questionName;
        this.answers = answers;
    }

    public String getQuestionName() {
        return questionName;
    }

    public void setQuestionName(String questionName) {
        this.questionName = questionName;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    @Override
    public String toString() {
        return "QuestionDto{" +
                "questionName='" + questionName + '\'' +
                ", answers=" + answers +
                '}';
    }
}
