package master.msc.model;

import javax.persistence.*;

@Entity
@Table(name = "question_answers")
public class QuestionAnswer extends MscBaseEntity {

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    private UserQuestionaryAnswers userQuestionaryAnswers;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    private Answer answer;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    private MaturityEvaluation maturityEvaluation;

    private String comment;

    public QuestionAnswer() {

    }

    public MaturityEvaluation getMaturityEvaluation() {
        return maturityEvaluation;
    }

    public void setMaturityEvaluation(MaturityEvaluation maturityEvaluation) {
        this.maturityEvaluation = maturityEvaluation;
    }

    public UserQuestionaryAnswers getUserQuestionaryAnswers() {
        return userQuestionaryAnswers;
    }

    public void setUserQuestionaryAnswers(UserQuestionaryAnswers userQuestionaryAnswers) {
        this.userQuestionaryAnswers = userQuestionaryAnswers;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
