package master.msc.model;

import master.msc.enums.AccountStatus;
import master.msc.enums.Role;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "employments")
public class Employment extends MscBaseEntity {

    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;
    private AccountStatus accountStatus;
    private LocalDateTime expirationDate;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    private Organization organization;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "employment_x_business_unit",
            joinColumns = {@JoinColumn(name = "employment_id")},
            inverseJoinColumns = {@JoinColumn(name = "business_unit_id")}
    )
    private List<BusinessUnit> departments = new ArrayList<>();

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    private Person person;

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY, mappedBy = "employment")
    private List<UserQuestionaryAnswers> userQuestionaryAnswers = new ArrayList<>();

    private boolean isTutorialWatched = true;
    private boolean isDeleted;

    @Transient
    private List<Functionality> functionalities = new ArrayList<>();

    public Employment() {

    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public Employment(Role role) {
        this.role = role;
    }

    public List<BusinessUnit> getDepartments() {
        return departments;
    }

    public void setDepartments(List<BusinessUnit> departments) {
        this.departments = departments;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public List<UserQuestionaryAnswers> getUserQuestionaryAnswers() {
        return userQuestionaryAnswers;
    }

    public void setUserQuestionaryAnswers(List<UserQuestionaryAnswers> userQuestionaryAnswers) {
        this.userQuestionaryAnswers = userQuestionaryAnswers;
    }

    public boolean getIsTutorialWatched() {
        return isTutorialWatched;
    }

    public void setIsTutorialWatched(boolean tutorialWatched) {
        isTutorialWatched = tutorialWatched;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public List<Functionality> getFunctionalities() {
        return functionalities;
    }

    public void setFunctionalities(List<Functionality> functionalities) {
        this.functionalities = functionalities;
    }
}
