package master.msc.model;

import javax.persistence.*;

@Entity
@Table(name = "question_target")
public class QuestionTarget extends MscBaseEntity {

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    private MaturityEvaluation maturityEvaluation;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    private Answer answer;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    private QuestionaryTarget questionaryTarget;

    private String comment;

    public QuestionTarget() {
        super();
    }

    public MaturityEvaluation getMaturityEvaluation() {
        return maturityEvaluation;
    }

    public void setMaturityEvaluation(MaturityEvaluation maturityEvaluation) {
        this.maturityEvaluation = maturityEvaluation;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public QuestionaryTarget getQuestionaryTarget() {
        return questionaryTarget;
    }

    public void setQuestionaryTarget(QuestionaryTarget questionaryTarget) {
        this.questionaryTarget = questionaryTarget;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
