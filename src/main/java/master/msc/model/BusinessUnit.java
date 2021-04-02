package master.msc.model;



import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "business_units")
public class BusinessUnit extends MscBaseEntity {

    private String name;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    private BusinessUnit parentUnit;

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, mappedBy = "parentUnit")
    private List<BusinessUnit> childUnits = new ArrayList<>();

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, mappedBy = "businessUnit")
    private List<MaturityEvaluation> maturityEvaluations = new ArrayList<>();

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, mappedBy = "businessUnit")
    private List<MachineGroup> machineGroups = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY, mappedBy = "departments")
    private List<Employment> employments = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY, mappedBy = "businessUnits")
    private List<Questionary> questionaries = new ArrayList<>();

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    private Organization organization;

    public BusinessUnit() {
    }

    public BusinessUnit(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Questionary> getQuestionaries() {
        return questionaries;
    }

    public void setQuestionaries(List<Questionary> questionaries) {
        this.questionaries = questionaries;
    }

    public BusinessUnit getParentUnit() {
        return parentUnit;
    }

    public void setParentUnit(BusinessUnit parentUnit) {
        this.parentUnit = parentUnit;
    }

    public List<BusinessUnit> getChildUnits() {
        return childUnits;
    }

    public void setChildUnits(List<BusinessUnit> childUnits) {
        this.childUnits = childUnits;
    }

    public List<MaturityEvaluation> getMaturityEvaluations() {
        return maturityEvaluations;
    }

    public void setMaturityEvaluations(List<MaturityEvaluation> maturityEvaluations) {
        this.maturityEvaluations = maturityEvaluations;
    }

    public List<MachineGroup> getMachineGroups() {
        return machineGroups;
    }

    public void setMachineGroups(List<MachineGroup> machineGroups) {
        this.machineGroups = machineGroups;
    }

    public List<Employment> getEmployments() {
        return employments;
    }

    public void setEmployments(List<Employment> employments) {
        this.employments = employments;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }
}
