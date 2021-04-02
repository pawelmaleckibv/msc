package master.msc.model;


import javax.persistence.*;

@Entity
@Table(name = "answer_dependency")
public class AnswerDependency extends MscBaseEntity {

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    private Answer answer;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    private Question dependentQuestion;
    private Integer minimumMaturityLevel;

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public Question getDependentQuestion() {
        return dependentQuestion;
    }

    public void setDependentQuestion(Question dependentQuestion) {
        this.dependentQuestion = dependentQuestion;
    }

    public Integer getMinimumMaturityLevel() {
        return minimumMaturityLevel;
    }

    public void setMinimumMaturityLevel(Integer minimumMaturityLevel) {
        this.minimumMaturityLevel = minimumMaturityLevel;
    }
}
