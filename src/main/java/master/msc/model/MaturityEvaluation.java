package master.msc.model;


import master.msc.enums.MaturityEvaluationStatus;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "maturity_evaluations")
public class MaturityEvaluation extends MscBaseEntity {

    private MaturityEvaluationStatus status;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    private BusinessUnit businessUnit;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    private MaturityLevelTransition maturityLevelTransition;

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, mappedBy = "maturityEvaluation")
    private List<QuestionAnswer> questionAnswers = new ArrayList<>();

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, mappedBy = "maturityEvaluation")
    private List<Task> tasks = new ArrayList<>();


    public MaturityEvaluation() {

    }

    public BusinessUnit getBusinessUnit() {
        return businessUnit;
    }

    public void setBusinessUnit(BusinessUnit businessUnit) {
        this.businessUnit = businessUnit;
    }

    public MaturityLevelTransition getMaturityLevelTransition() {
        return maturityLevelTransition;
    }

    public void setMaturityLevelTransition(MaturityLevelTransition maturityLevelTransition) {
        this.maturityLevelTransition = maturityLevelTransition;
    }

    public List<QuestionAnswer> getQuestionAnswers() {
        return questionAnswers;
    }

    public void setQuestionAnswers(List<QuestionAnswer> questionAnswers) {
        this.questionAnswers = questionAnswers;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public MaturityEvaluationStatus getStatus() {
        return status;
    }

    public void setStatus(MaturityEvaluationStatus status) {
        this.status = status;
    }
}
