package master.msc.model;

import master.msc.enums.MaturityLevelTransitionStatus;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "maturity_level_transitions")
public class MaturityLevelTransition extends MscBaseEntity {

    private MaturityLevelTransitionStatus status;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    private MaturityLevel currentMaturityLevel;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    private MaturityLevel targetMaturityLevel;

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, mappedBy = "maturityLevelTransition")
    private List<MaturityEvaluation> maturityEvaluations = new ArrayList<>();

    public MaturityLevelTransition() {

    }

    public MaturityLevel getCurrentMaturityLevel() {
        return currentMaturityLevel;
    }

    public void setCurrentMaturityLevel(MaturityLevel currentMaturityLevel) {
        this.currentMaturityLevel = currentMaturityLevel;
    }

    public MaturityLevel getTargetMaturityLevel() {
        return targetMaturityLevel;
    }

    public void setTargetMaturityLevel(MaturityLevel targetMaturityLevel) {
        this.targetMaturityLevel = targetMaturityLevel;
    }

    public List<MaturityEvaluation> getMaturityEvaluations() {
        return maturityEvaluations;
    }

    public void setMaturityEvaluations(List<MaturityEvaluation> maturityEvaluations) {
        this.maturityEvaluations = maturityEvaluations;
    }

    public MaturityLevelTransitionStatus getStatus() {
        return status;
    }

    public void setStatus(MaturityLevelTransitionStatus status) {
        this.status = status;
    }
}
