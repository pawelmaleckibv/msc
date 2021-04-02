package master.msc.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "questionary_target")
public class QuestionaryTarget extends MscBaseEntity {

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    private Employment employment;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    private Questionary questionary;

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    private List<QuestionTarget> questionTargets = new ArrayList<>();

    public QuestionaryTarget() {
        super();
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

    public List<QuestionTarget> getQuestionTargets() {
        return questionTargets;
    }

    public void setQuestionTargets(List<QuestionTarget> questionTargets) {
        this.questionTargets = questionTargets;
    }
}

