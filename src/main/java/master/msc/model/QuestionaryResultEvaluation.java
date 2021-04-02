package master.msc.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "questionary_result_evaluation")
public class QuestionaryResultEvaluation extends MscBaseEntity {

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    private Employment employment;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    private Questionary questionary;

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    private List<QuestionMaturityLevel> questionMaturityLevel = new ArrayList<>();

    public QuestionaryResultEvaluation() {
    }

    public Employment getEmployment() {
        return employment;
    }

    public void setEmployment(Employment employment) {
        this.employment = employment;
    }

    public Questionary getQuestionary() {
        return questionary;
    }

    public void setQuestionary(Questionary questionary) {
        this.questionary = questionary;
    }

    public List<QuestionMaturityLevel> getQuestionMaturityLevel() {
        return questionMaturityLevel;
    }

    public void setQuestionMaturityLevel(List<QuestionMaturityLevel> questionMaturityLevel) {
        this.questionMaturityLevel = questionMaturityLevel;
    }
}

