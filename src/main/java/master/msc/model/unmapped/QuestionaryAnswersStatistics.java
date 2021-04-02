package master.msc.model.unmapped;


import master.msc.model.MscBaseEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuestionaryAnswersStatistics extends MscBaseEntity {
    public String questionaryName;
    public Map<String, String> questions = new HashMap<>();
    public List<Integer> numberOfAnswers = new ArrayList<>();
    public List<Integer> averageAnswerMaturity = new ArrayList<>();

    public QuestionaryAnswersStatistics(String questionaryName, Map<String, String>  questions, List<Integer> numberOfAnswers, List<Integer> averageAnswerMaturity) {
        this.questionaryName = questionaryName;
        this.questions = questions;
        this.numberOfAnswers = numberOfAnswers;
        this.averageAnswerMaturity = averageAnswerMaturity;
    }

    public QuestionaryAnswersStatistics() {
    }

    public String getQuestionaryName() {
        return questionaryName;
    }

    public void setQuestionaryName(String questionaryName) {
        this.questionaryName = questionaryName;
    }

    public Map<String, String> getQuestions() {
        return questions;
    }

    public void setQuestions(Map<String, String> questions) {
        this.questions = questions;
    }

    public List<Integer> getNumberOfAnswers() {
        return numberOfAnswers;
    }

    public void setNumberOfAnswers(List<Integer> numberOfAnswers) {
        this.numberOfAnswers = numberOfAnswers;
    }

    public List<Integer> getAverageAnswerMaturity() {
        return averageAnswerMaturity;
    }

    public void setAverageAnswerMaturity(List<Integer> averageAnswerMaturity) {
        this.averageAnswerMaturity = averageAnswerMaturity;
    }
}
