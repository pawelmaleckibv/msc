package master.msc.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "questionaries")
public class Questionary extends MscBaseEntity {

    private String name;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "questionary_x_business_unit",
            joinColumns = {@JoinColumn(name = "questionary_id")},
            inverseJoinColumns = {@JoinColumn(name = "business_unit_id")}
    )
    private List<BusinessUnit> businessUnits = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "questionary_x_question",
            joinColumns = {@JoinColumn(name = "questionary_id")},
            inverseJoinColumns = {@JoinColumn(name = "question_id")}
    )
    private List<Question> questions = new ArrayList<>();


    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY, mappedBy = "questionary")
    private List<UserQuestionaryAnswers> userQuestionaryAnswers = new ArrayList<>();

    private boolean isDeleted;

    public Questionary() {

    }

    public Questionary(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BusinessUnit> getBusinessUnits() {
        return businessUnits;
    }

    public void setBusinessUnits(List<BusinessUnit> businessUnits) {
        this.businessUnits = businessUnits;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public List<UserQuestionaryAnswers> getUserQuestionaryAnswers() {
        return userQuestionaryAnswers;
    }

    public void setUserQuestionaryAnswers(List<UserQuestionaryAnswers> userQuestionaryAnswers) {
        this.userQuestionaryAnswers = userQuestionaryAnswers;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
