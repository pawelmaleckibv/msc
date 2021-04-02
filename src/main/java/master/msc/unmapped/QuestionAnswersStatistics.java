package master.msc.unmapped;


import master.msc.model.Answer;
import master.msc.model.Question;

import java.util.HashMap;
import java.util.Map;

public class QuestionAnswersStatistics {
    public Question question;
    public Map<Answer, Integer> questionAnswersStatistics = new HashMap<>();

    public QuestionAnswersStatistics(Question question, Map<Answer, Integer> questionAnswersStatistics) {
        this.question = question;
        this.questionAnswersStatistics = questionAnswersStatistics;
    }

    public QuestionAnswersStatistics(){}

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Map<Answer, Integer> getQuestionAnswersStatistics() {
        return questionAnswersStatistics;
    }

    public void setQuestionAnswersStatistics(Map<Answer, Integer> questionAnswersStatistics) {
        this.questionAnswersStatistics = questionAnswersStatistics;
    }
}
