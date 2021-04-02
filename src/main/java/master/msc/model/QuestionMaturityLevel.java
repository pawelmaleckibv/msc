package master.msc.model;

import javax.persistence.*;

@Entity
@Table(name = "question_maturity_level")
public class QuestionMaturityLevel extends MscBaseEntity {

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    private QuestionaryResultEvaluation questionaryResultEvaluation;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    private Answer answer;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    private MaturityEvaluation maturityEvaluation;

    private String comment;

    public QuestionMaturityLevel() {
    }

    public QuestionaryResultEvaluation getQuestionaryResultEvaluation() {
        return questionaryResultEvaluation;
    }

    public void setQuestionaryResultEvaluation(QuestionaryResultEvaluation questionaryResultEvaluation) {
        this.questionaryResultEvaluation = questionaryResultEvaluation;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public MaturityEvaluation getMaturityEvaluation() {
        return maturityEvaluation;
    }

    public void setMaturityEvaluation(MaturityEvaluation maturityEvaluation) {
        this.maturityEvaluation = maturityEvaluation;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
